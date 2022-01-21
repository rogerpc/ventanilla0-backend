package com.vcero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.app.AppFile;
import com.vcero.entity.view.VDocumentacion;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/tramite/documentacion")
public class DocumentacionController {

	@Autowired
	private QueryBuilder queryBuilder;

	@PostMapping("/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(VDocumentacion.class, query, result);
	}

	@GetMapping("/{id:[0-9]+}/archivo")
	public ResponseEntity<?> showDocumento(@PathVariable Integer id) {
		VDocumentacion documentacionDB = queryBuilder.findByIdEx404(VDocumentacion.class, id);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.body(AppFile.toByteArrayDocumentacion(documentacionDB));
	}

}
