package com.vcero.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.app.AppFile;
import com.vcero.app.exception.BadRequestException;
import com.vcero.app.response.AppResponse;
import com.vcero.app.type.TypeEntity;
import com.vcero.app.type.TypeError;
import com.vcero.app.validation.AppValidation;
import com.vcero.entity.Documentacion;
import com.vcero.entity.Persona;
import com.vcero.entity.Tramite;
import com.vcero.entity.TramitePersona;
import com.vcero.entity.catalogos.EdoTramite;
import com.vcero.entity.view.VTramitePersona;
import com.vcero.model.Validar;
import com.vcero.model.ValidarDoc;
import com.vcero.repository.TramitePersonaRepository;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/tramite")
public class TramitePersonaController {

	@Autowired
	private QueryBuilder queryBuilder;
	@Autowired
	private TramitePersonaRepository tramitePersonaRepository;
	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping("/persona/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(VTramitePersona.class, query, result);
	}

	@PutMapping("/{idtramiteper:[0-9]+}/persona")
	public ResponseEntity<?> update(@PathVariable Integer idtramiteper, @Valid Validar tramitePersona,
			BindingResult result) throws IOException {
		AppValidation.exIfRequestModelHasErrors(tramitePersona, result);
		if (EdoTramite.R.SOLICITUD.id() == tramitePersona.getIdedotramite()) {
			throw new BadRequestException(
					TypeEntity.EDO_TRAMITE.singular() + ": " + EdoTramite.R.SOLICITUD + " No valido");
		}
		// Tramite
		TramitePersona tramitePersonaDB = queryBuilder.findByIdEx404(TramitePersona.class, idtramiteper);
		if (EdoTramite.R.SUBSANAR.id() == tramitePersona.getIdedotramite()) {
			Tramite tramiteDB = queryBuilder.findByIdEx404(Tramite.class, tramitePersonaDB.getIdtramite());
			if (!tramiteDB.getSubsanar()) {
				throw new BadRequestException(
						TypeEntity.EDO_TRAMITE.singular() + ": " + EdoTramite.R.SUBSANAR + " No valido");
			}
		}
		if (!(EdoTramite.R.SOLICITUD.id() == tramitePersonaDB.getIdedotramite()
				|| EdoTramite.R.SUBSANAR.id() == tramitePersonaDB.getIdedotramite())) {
			throw new BadRequestException(
					TypeEntity.EDO_TRAMITE.requiered(EdoTramite.R.SOLICITUD, EdoTramite.R.SUBSANAR));
		}
		if (tramitePersona.getDocumentacion().size() != tramitePersonaDB.getDocumentacion().size()) {
			throw new BadRequestException(TypeError.ERR_DOCUMENTACION.msg());
		}
		List<Integer> errIddoc = new ArrayList<Integer>();
		List<Integer> errDocVal = new ArrayList<Integer>();
		for (Documentacion documentoDB : tramitePersonaDB.getDocumentacion()) {
			ValidarDoc doc = tramitePersona.getDocumentacion().stream()
					.filter(d -> d.getIddoc() == documentoDB.getIddoc()).findFirst().orElse(null);
			if (doc == null) {
				errIddoc.add(documentoDB.getIddoc());
			} else {
				documentoDB.setDocvalido(doc.getDocvalido());
			}
			if (EdoTramite.R.AUTORIZADO.id() == tramitePersona.getIdedotramite() && !doc.getDocvalido()) {
				errDocVal.add(documentoDB.getIddoc());
			}
		}
		if (!errIddoc.isEmpty()) {
			throw new BadRequestException(TypeEntity.DOCUMENTO.requiered(errIddoc));
		}
		if (!errDocVal.isEmpty()) {
			throw new BadRequestException("Todos los documentos deben ser validados");
		}

		tramitePersonaDB.setIdedotramite(tramitePersona.getIdedotramite());
		tramitePersonaRepository.save(tramitePersonaDB); // save
		AppFile.updateDocumentacion(tramitePersonaDB, tramitePersona.getDocumentacion());
		tramitePersonaRepository.save(tramitePersonaDB); // save name
		// enviar email
		Persona persona = queryBuilder.findByIdEx400(Persona.class, tramitePersonaDB.getIdpersona());
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(persona.getEmailpersona());
		msg.setSubject("Ventanilla Cero");
		msg.setText("Su solicitud cambio de estado.");
		javaMailSender.send(msg);
		return AppResponse.update(tramitePersonaDB);
	}

}
