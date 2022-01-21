package com.vcero.app.exception;

import com.vcero.app.type.TypeEntity;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(TypeEntity type) {
		super(type.notFound());
	}

	public NotFoundException(TypeEntity type, Integer id) {
		super(type.notFound(id));
	}

	public NotFoundException(TypeEntity type, String id) {
		super(type.notFound(id));
	}

}