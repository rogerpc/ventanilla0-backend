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
import com.vcero.entity.Documento;
import com.vcero.repository.DocumentoRepository;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	private QueryBuilder queryBuilder;
	@Autowired
	private DocumentoRepository documentoRepository;

	@PostMapping("/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(Documento.class, query, result);
	}

	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Documento documentoDB = queryBuilder.findByIdEx404(Documento.class, id);
		return AppResponse.ok(documentoDB.type().singular(), documentoDB);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody(required = false) Documento documento, BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(documento, result);
		return AppResponse.save(documentoRepository.save(documento));
	}

	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody(required = false) Documento documento,
			BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(documento, result);
		Documento documentoDB = queryBuilder.findByIdEx404(Documento.class, id);
		// Update
		documentoDB.setNombredoc(documento.getNombredoc());
		documentoDB.setDescdoc(documento.getDescdoc());
		return AppResponse.update(documentoRepository.save(documentoDB));
	}

}
