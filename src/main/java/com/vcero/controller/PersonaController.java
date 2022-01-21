package com.vcero.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.app.response.AppResponse;
import com.vcero.model.Ciudadano;
import com.vcero.service.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@PostMapping("/ciudadano")
	public ResponseEntity<?> save(@Valid Ciudadano ciudadano, BindingResult result) throws IOException {
		return AppResponse.save(personaService.save(ciudadano, result));
	}

}
