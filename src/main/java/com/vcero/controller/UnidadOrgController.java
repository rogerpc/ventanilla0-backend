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
import com.vcero.entity.UnidadOrg;
import com.vcero.entity.view.VUnidadOrg;
import com.vcero.repository.UnidadOrgRepository;
import com.vcero.repository.builder.Query;
import com.vcero.repository.builder.QueryBuilder;

@RestController
@RequestMapping("/organizacion/unidad")
public class UnidadOrgController {

	@Autowired
	private QueryBuilder queryBuilder;
	@Autowired
	private UnidadOrgRepository unidadRepository;

	@PostMapping("/data")
	public ResponseEntity<?> list(@Valid @RequestBody(required = false) Query query, BindingResult result) {
		return queryBuilder.responseEntityFind(VUnidadOrg.class, query, result);
	}

	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<?> show(@PathVariable Integer id, @RequestParam(defaultValue = "false") Boolean view) {
		if (view) {
			VUnidadOrg unidadOrgDB = queryBuilder.findByIdEx404(VUnidadOrg.class, id);
			return AppResponse.ok(unidadOrgDB.type().singular(), unidadOrgDB);
		}
		UnidadOrg unidadOrgDB = queryBuilder.findByIdEx404(UnidadOrg.class, id);
		return AppResponse.ok(unidadOrgDB.type().singular(), unidadOrgDB);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody(required = false) UnidadOrg unidad, BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(unidad, result);
		return AppResponse.save(unidadRepository.save(unidad));
	}

	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody(required = false) UnidadOrg unidad,
			BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(unidad, result);
		UnidadOrg unidadDB = queryBuilder.findByIdEx404(UnidadOrg.class, id);
		// Update
		unidadDB.setNombreunidad(unidad.getNombreunidad());
		unidadDB.setIdorg(unidad.getIdorg());
		return AppResponse.update(unidadRepository.save(unidadDB));
	}

}
