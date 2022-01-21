package com.vcero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.entity.view.VRequisitos;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/requisitos")
public class RequisitosController {

	@Autowired
	private QueryBuilder queryBuilder;
	
	@PostMapping("/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query,
			BindingResult result) {
		return queryBuilder.responseEntityFind(VRequisitos.class, query, result);
	}
	
}
