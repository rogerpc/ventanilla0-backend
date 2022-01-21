package com.vcero.app.response;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vcero.entity.EntityStatus;

public class AppResponse {

	private static String INFO_SAVE = "Información guardada";
	private static String INFO_UPDATE = "Información actualizada";
	private static String INFO_DELETE = "Información eliminada";
	private static String IMAGEN_SAVE = "Imagen actualizada";

	public static ResponseEntity<HttpOk> ok(String message, Object detail) {
		return ResponseEntity.ok(new HttpOk(message, detail));
	}

	public static ResponseEntity<HttpOk> save(Object detail) {
		return ok(INFO_SAVE, detail);
	}

	public static ResponseEntity<HttpOk> update(Object detail) {
		return ok(INFO_UPDATE, detail);
	}

	public static ResponseEntity<HttpOk> updateImagen(Object detail) {
		return ok(IMAGEN_SAVE, detail);
	}

	public static ResponseEntity<HttpOk> delete(Object detail) {
		return ok(INFO_DELETE, detail);
	}

	public static <T extends EntityStatus> ResponseEntity<HttpOk> status(T entity) {
		return ok(entity.type().status(entity.getStatus(), entity.id()), entity);
	}

	public static ResponseEntity<byte[]> mediaBodyJpg(byte[] bytes) {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
	}

	public static ResponseEntity<HttpError> exception(HttpStatus httpStatus, String message, String path) {
		return ResponseEntity.status(httpStatus)
				.body(new HttpError(httpStatus.value(), httpStatus.getReasonPhrase(), message, path));
	}

	public static ResponseEntity<HttpError> exception(HttpStatus httpStatus, String message, String path,
			Object detail) {
		return ResponseEntity.status(httpStatus)
				.body(new HttpError(httpStatus.value(), httpStatus.getReasonPhrase(), message, path, detail));
	}

	public static void handlerException(HttpServletResponse response, HttpServletRequest request, HttpStatus httpStatus,
			String message) throws JsonProcessingException, IOException {
		handlerException(response, request, httpStatus, message, new HashMap<>());
	}

	public static void handlerException(HttpServletResponse response, HttpServletRequest request, HttpStatus httpStatus,
			String message, Object detail) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(httpStatus.value());
		response.getWriter().append(mapper.writeValueAsString(new HttpError(httpStatus.value(),
				httpStatus.getReasonPhrase(), message, request.getRequestURI(), detail)));
	}

}
