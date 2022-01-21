package com.vcero.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.vcero.app.response.AppResponse;
import com.vcero.app.validation.AppValidation;
import com.vcero.entity.Organizacion;
import com.vcero.repository.OrganizacionRepository;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/organizacion")
public class OrganizacionController {

	@Autowired
	private QueryBuilder queryBuilder;
	@Autowired
	private OrganizacionRepository organizacionRepository;

	@PostMapping("/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(Organizacion.class, query, result);
	}

	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Organizacion organizacionDB = queryBuilder.findByIdEx404(Organizacion.class, id);
		return AppResponse.ok(organizacionDB.type().singular(), organizacionDB);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody(required = false) Organizacion organizacion,
			BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(organizacion, result);
		return AppResponse.save(organizacionRepository.save(organizacion));
	}

	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<?> update(@PathVariable Integer id,
			@Valid @RequestBody(required = false) Organizacion organizacion, BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(organizacion, result);
		Organizacion organizacionDB = queryBuilder.findByIdEx404(Organizacion.class, id);
		// Update
		organizacionDB.setNombreorg(organizacion.getNombreorg());
		organizacionDB.setNcortoorg(organizacion.getNcortoorg());
		return AppResponse.update(organizacionRepository.save(organizacionDB));
	}

}
