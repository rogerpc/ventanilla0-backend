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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.app.response.AppResponse;
import com.vcero.app.validation.AppValidation;
import com.vcero.entity.catalogos.Ejercicio;
import com.vcero.repository.EjercicioRepository;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/ejercicio")
public class EjercicioController {

	@Autowired
	private QueryBuilder queryBuilder;
	@Autowired
	private EjercicioRepository ejercicioRepository;

	@PostMapping("/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(Ejercicio.class, query, result);
	}

	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<?> show(@PathVariable Integer id, @RequestParam(required = false) Boolean status,
			@RequestParam(defaultValue = "false") Boolean view) {
		return queryBuilder.responseEntityFindById(Ejercicio.class, id, status);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody(required = false) Ejercicio ejercicio, BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(ejercicio, result);
		ejercicioRepository.updateAllStatusFalse();
		return AppResponse.save(ejercicioRepository.save(ejercicio));
	}

	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody(required = false) Ejercicio ejercicio,
			BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(ejercicio, result);
		Ejercicio ejercicioDB = queryBuilder.findByIdEx404(Ejercicio.class, id);
		ejercicioDB.setAnioejercicio(ejercicio.getAnioejercicio());
		return AppResponse.update(ejercicioRepository.save(ejercicioDB));
	}

}
