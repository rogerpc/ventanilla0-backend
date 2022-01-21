package com.vcero.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.app.exception.BadRequestException;
import com.vcero.app.response.AppResponse;
import com.vcero.app.type.TypeEntity;
import com.vcero.app.validation.AppValidation;
import com.vcero.entity.Documento;
import com.vcero.entity.Requisitos;
import com.vcero.entity.Tramite;
import com.vcero.entity.UnidadOrg;
import com.vcero.entity.view.VTramite;
import com.vcero.repository.TramiteRepository;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/tramite")
public class TramiteController {

	@Autowired
	private QueryBuilder queryBuilder;
	@Autowired
	private TramiteRepository tramiteRepository;

	@PostMapping("/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(VTramite.class, query, result);
	}

	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<?> show(@PathVariable Integer id, @RequestParam(required = false) Boolean status,
			@RequestParam(defaultValue = "false") Boolean view) {
		return view ? queryBuilder.responseEntityFindById(VTramite.class, id, status)
				: queryBuilder.responseEntityFindById(Tramite.class, id, status);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody(required = false) Tramite tramite, BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(tramite, result);
		queryBuilder.findByIdEx400(UnidadOrg.class, tramite.getIdunidad());
		List<Integer> iddocs = tramite.getRequisitos().stream().map(r -> r.getIddoc()).collect(Collectors.toList());
		AppValidation.exIfContainsRepeatedValuesInt(TypeEntity.DOCUMENTO, iddocs);
		for (Requisitos requisito : tramite.getRequisitos()) {
			queryBuilder.findByIdEx400(Documento.class, requisito.getIddoc());
		}
		if (tramiteRepository.existsByCveservicio(tramite.getCveservicio())) {
			throw new BadRequestException(tramite.type().exists(tramite.getCveservicio()));
		}
		tramite.setSecuencia(1);
		return AppResponse.save(tramiteRepository.save(tramite));
	}

	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody(required = false) Tramite tramite,
			BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(tramite, result);
		Tramite tramiteDB = queryBuilder.findByIdEx404(Tramite.class, id);
		queryBuilder.findByIdEx400(UnidadOrg.class, tramite.getIdunidad());
		if (!tramiteDB.getCveservicio().equals(tramite.getCveservicio())
				&& tramiteRepository.existsByCveservicio(tramite.getCveservicio())) {
			throw new BadRequestException(tramite.type().exists(tramite.getCveservicio()));
		}
		// Update
		tramiteDB.setNombretramite(tramite.getNombretramite());
		tramiteDB.setDesctramite(tramite.getDesctramite());
		tramiteDB.setResolucion(tramite.getResolucion());
		tramiteDB.setCveservicio(tramite.getCveservicio());
		tramiteDB.setIdunidad(tramite.getIdunidad());
		tramiteDB.setSubsanar(tramite.getSubsanar());
		//
		List<Requisitos> requisitosDel = new ArrayList<Requisitos>();
		for (Requisitos requisitoDB : tramiteDB.getRequisitos()) {
			Requisitos requisitoUpd = tramite.getRequisitos().stream()
					.filter(requisito -> requisito.getIddoc().equals(requisitoDB.getIddoc())).findFirst().orElse(null);
			if (Objects.isNull(requisitoUpd)) {
				requisitosDel.add(requisitoDB);
			} else {
				requisitoDB.setRequerido(requisitoUpd.getRequerido()); // <--- update
			}
		}
		requisitosDel.forEach(requisitoDel -> {
			tramiteDB.removeRequisito(requisitoDel); // <--- delete
		});
		for (Requisitos requisito : tramite.getRequisitos()) {
			Boolean exists = tramiteDB.getRequisitos().stream()
					.filter(requisitoDB -> requisitoDB.getIddoc().equals(requisito.getIddoc())).findFirst().isPresent();
			if (!exists) {
				tramiteDB.addRequisito(requisito); // <--- insert
			}
		}
		return AppResponse.update(tramiteRepository.save(tramiteDB));
	}

	@PutMapping("/{id:[0-9]+}/status/{status:true|false}")
	public ResponseEntity<?> status(@PathVariable Integer id, @PathVariable Boolean status) {
		Tramite tramiteDB = queryBuilder.findByIdEx404(Tramite.class, id);
		AppValidation.exIfStatusAreEqual(tramiteDB, status);
		// Status
		if (status) {
		} else {
		}
		// Update
		tramiteDB.onStatus(status);
		return AppResponse.status(tramiteRepository.save(tramiteDB));
	}
}
