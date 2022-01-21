package com.vcero.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.entity.catalogos.Colonia;
import com.vcero.entity.catalogos.EdoTramite;
import com.vcero.entity.catalogos.Estado;
import com.vcero.entity.catalogos.Municipio;
import com.vcero.entity.catalogos.Registro;
import com.vcero.entity.view.VCodigoPostal;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/catalogo")
public class CatalogosController {

	@Autowired
	private QueryBuilder queryBuilder;

	@PostMapping("/colonia")
	public ResponseEntity<?> listColonia(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(Colonia.class, query, result);
	}

	@PostMapping("/estado/tramite")
	public ResponseEntity<?> listEdoTramite(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(EdoTramite.class, query, result);
	}

	@PostMapping("/estado")
	public ResponseEntity<?> listEstado(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(Estado.class, query, result);
	}

	@PostMapping("/municipio")
	public ResponseEntity<?> listMunicipio(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(Municipio.class, query, result);
	}

	@PostMapping("/cp")
	public ResponseEntity<?> listCodigoPostal(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(VCodigoPostal.class, query, result);
	}

	@PostMapping("/registro")
	public ResponseEntity<?> listRegistro(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(Registro.class, query, result);
	}
}
