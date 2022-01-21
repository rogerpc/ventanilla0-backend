package com.vcero.app.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class BadRequestMapException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Map<String, String> detail = new HashMap<>();

	public BadRequestMapException(String message, BindingResult result) {
		super(message);
		result.getFieldErrors().forEach(err -> {
			this.detail.put(err.getField(), err.getDefaultMessage());
		});
	}

	public BadRequestMapException(String message, Map<String, String> detail) {
		super(message);
		this.detail = detail;
	}

	public BadRequestMapException(String key, Integer index, String value) {
		super(key + "[" + index + "] " + value);
		this.detail = Collections.singletonMap(key + "[" + index + "] ", value);
	}

	public BadRequestMapException(String key, String value) {
		super(key + " " + value);
		this.detail = Collections.singletonMap(key, value);
	}

	public Map<String, String> getDetail() {
		return detail;
	}

}
