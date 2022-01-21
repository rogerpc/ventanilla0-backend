package com.vcero.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcero.app.response.AppResponse;
import com.vcero.app.validation.AppValidation;
import com.vcero.entity.ValidarEmail;
import com.vcero.repository.ValidarEmailRespository;

@RestController
@RequestMapping("/validar/email")
public class ValidarEmailController {

	@Autowired
	private ValidarEmailRespository validarEmailRespository;
	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping
	public ResponseEntity<?> emailValidar(@Valid @RequestBody(required = false) ValidarEmail validar,
			BindingResult result) {
		AppValidation.exIfRequestModelHasErrors(validar, result);
		validarEmailRespository.deleteByEmail(validar.getEmail());
		validarEmailRespository.save(validar);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(validar.getEmail());
		msg.setSubject("Ventanilla Cero");
		msg.setText("Codigo: " + validar.getIdvalidar());
		javaMailSender.send(msg);
		return AppResponse.update(Map.of("email", validar.getEmail()));
	}

}
