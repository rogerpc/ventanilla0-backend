package com.vcero.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.vcero.app.exception.BadRequestException;
import com.vcero.app.exception.BadRequestMapException;
import com.vcero.app.exception.ForbiddenException;
import com.vcero.app.exception.NotFoundException;
import com.vcero.app.exception.UnauthorizedException;
import com.vcero.app.response.AppResponse;
import com.vcero.app.type.TypeErrorArgs;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
		return AppResponse.exception(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleBadRequestException(NotFoundException e, HttpServletRequest request) {
		return AppResponse.exception(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(BadRequestMapException.class)
	public ResponseEntity<?> handleNotFoundException(BadRequestMapException e, HttpServletRequest request) {
		return AppResponse.exception(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI(), e.getDetail());
	}

	@ExceptionHandler(InvalidFormatException.class) // Serializacion de Beans
	public ResponseEntity<?> handleBadRequestInvalidFormatExceptionX(InvalidFormatException e,
			HttpServletRequest request) {
		String message = "'" + e.getValue() + "' no es un valor valido";
		String detail = "Valor valido requerido";
		if (e.getTargetType().isEnum()) {
			String type = StringUtils.join(Arrays.asList(e.getTargetType().getEnumConstants()), ", ");
			detail = TypeErrorArgs.ERR_DESERIALIZATION_BEAN_x.msg(type);
		} else if (e.getTargetType().isArray()) {
			detail = "Lista de valores requerida";
		} else if (e.getTargetType().equals(Integer.class)) {
			message = "'" + e.getValue() + "' no es un numero";
			detail = "Número requerido";
		} else if (e.getTargetType().equals(Boolean.class)) {
			message = "'" + e.getValue() + "' no es un valor booleano";
			detail = "Valor booleano requerido";
		} else if (e.getTargetType().equals(Double.class)) {
			message = "'" + e.getValue() + "' no es un valor numerico";
			detail = "Número de punto flotante requerido";
		} else if (e.getTargetType().equals(Date.class)) {
			message = "'" + e.getValue() + "' no es un formato de fecha valido";
			detail = TypeErrorArgs.ERR_DESERIALIZATION_DATE_x.msg("");
		} else {
			System.out.println("ERR_DESERIALIZATION_BEAN: " + e.getTargetType());
		}
		//
		Map<String, String> errDetail = new HashMap<String, String>();
		String name = e.getPath().get(e.getPath().size() - 1).getFieldName();
		errDetail.put(name, detail);
		return AppResponse.exception(HttpStatus.BAD_REQUEST, message, request.getRequestURI(), errDetail);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e, HttpServletRequest request) {
		return AppResponse.exception(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<?> handlerForbiddenException(ForbiddenException e, HttpServletRequest request) {
		return AppResponse.exception(HttpStatus.FORBIDDEN, e.getMessage(), request.getRequestURI());
	}

}
