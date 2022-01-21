package com.vcero.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.vcero.app.AppFile;
import com.vcero.app.exception.BadRequestException;
import com.vcero.app.type.TypeEntity;
import com.vcero.app.type.TypeError;
import com.vcero.app.validation.AppValidation;
import com.vcero.entity.Documentacion;
import com.vcero.entity.Persona;
import com.vcero.entity.Requisitos;
import com.vcero.entity.Tramite;
import com.vcero.entity.TramitePersona;
import com.vcero.entity.ValidarEmail;
import com.vcero.entity.catalogos.EdoTramite;
import com.vcero.entity.catalogos.Ejercicio;
import com.vcero.entity.catalogos.Registro;
import com.vcero.model.Ciudadano;
import com.vcero.model.CiudadanoDoc;
import com.vcero.repository.DocumentoRepository;
import com.vcero.repository.EjercicioRepository;
import com.vcero.repository.PersonaRepository;
import com.vcero.repository.TramitePersonaRepository;
import com.vcero.repository.TramiteRepository;
import com.vcero.repository.ValidarEmailRespository;

@Service
@Transactional
public class PersonaService {

	private Integer ID_TRAMITE_GENERAL = 1;

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private DocumentoRepository documentoRepository;
	@Autowired
	private TramitePersonaRepository tramitePersonaRepository;
	@Autowired
	private TramiteRepository tramiteRepository;
	@Autowired
	private EjercicioRepository ejercicioRepository;
	@Autowired
	private ValidarEmailRespository validarEmailRespository;
	@Autowired
	private JavaMailSender javaMailSender;

	public TramitePersona save(Ciudadano ciudadano, BindingResult result) throws IOException {
		AppValidation.exIfRequestModelHasErrors(ciudadano, result);
		// Codigo
		ValidarEmail validar = validarEmailRespository.findById(ciudadano.getCodigo())
				.orElseThrow(() -> new BadRequestException(TypeEntity.VALIDAR_EMAIL.notFound()));
		if (!validar.getEmail().equals(ciudadano.getPersona().getEmailpersona())) {
			throw new BadRequestException(TypeEntity.VALIDAR_EMAIL.notFound(ciudadano.getCodigo()));
		}
		validarEmailRespository.deleteById(ciudadano.getCodigo());
		// Tramite
		Tramite tramiteDB = tramiteRepository.findById(ID_TRAMITE_GENERAL)
				.orElseThrow(() -> new RuntimeException(TypeEntity.TRAMITE.notFound(ID_TRAMITE_GENERAL)));
		if (ciudadano.getDocumentacion().size() != tramiteDB.getRequisitos().size()) {
			throw new BadRequestException(TypeError.ERR_DOCUMENTACION.msg());
		}
		List<Integer> errIddoc = new ArrayList<Integer>();
		List<String> errFile = new ArrayList<String>();
		List<Documentacion> documentacion = new ArrayList<Documentacion>();
		for (Requisitos requisitoDB : tramiteDB.getRequisitos()) {
			CiudadanoDoc doc = ciudadano.getDocumentacion().stream().filter(d -> d.getIddoc() == requisitoDB.getIddoc())
					.findFirst().orElse(null);
			if (doc == null) {
				errIddoc.add(requisitoDB.getIddoc());
			} else if (requisitoDB.getRequerido() && doc.getFiledoc() == null) {
				errFile.add(documentoRepository.findById(doc.getIddoc()).get().getNombredoc());
			} else {
				documentacion.add(new Documentacion(requisitoDB.getIddoc(), requisitoDB.getRequerido(), false));
			}
		}
		if (!errIddoc.isEmpty()) {
			throw new BadRequestException(TypeEntity.DOCUMENTO.requiered(errIddoc));
		}
		if (!errFile.isEmpty()) {
			throw new BadRequestException(
					TypeEntity.DOCUMENTACION.requiered() + ": " + StringUtils.join(errFile, ", "));
		}
		// Persona
		Persona persona = ciudadano.getPersona();
		if (personaRepository.existsByCurppersona(persona.getCurppersona())) {
			throw new BadRequestException("CURP registrado");
		}
		if (personaRepository.existsByRfcpersona(persona.getRfcpersona())) {
			throw new BadRequestException("RFC registrado");
		}
		persona.setIdpersona(null);
		personaRepository.save(persona);
		// Tramite
		Ejercicio ejercicioDB = ejercicioRepository.findFirstByStatusTrueOrderByIdejercicioDesc()
				.orElseThrow(() -> new RuntimeException(TypeEntity.EJERCICIO.notFound()));
		TramitePersona tramitePersona = new TramitePersona();
		tramitePersona.setIdpersona(persona.getIdpersona());
		tramitePersona.setIdtramite(ID_TRAMITE_GENERAL);
		tramitePersona.setIdedotramite(EdoTramite.R.SOLICITUD.id());
		tramitePersona.setIdregistro(Registro.R.ONLINE.id());
		tramitePersona.setIdejercicio(ejercicioDB.getIdejercicio());
		tramitePersona.setConsecutivo(tramiteDB.getSecuencia());
		tramitePersona.setDocumentacion(documentacion);
		tramitePersonaRepository.save(tramitePersona); // save
		AppFile.saveDocumentacion(tramitePersona, ciudadano.getDocumentacion());
		tramitePersonaRepository.save(tramitePersona); // save name
		//
		tramiteDB.setSecuencia(tramiteDB.getSecuencia() + 1);
		tramiteRepository.save(tramiteDB);
		// enviar email
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(validar.getEmail());
		msg.setSubject("Ventanilla Cero");
		msg.setText("Su solicitud se ha registrado correctamente. Procederemos a evaluar la documentación enviada "
				+ " y se le notificara al correo electrónico.");
		javaMailSender.send(msg);
		//
		return tramitePersona;
	}

}
