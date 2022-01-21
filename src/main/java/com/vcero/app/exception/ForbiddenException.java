package com.vcero.app.exception;

import com.vcero.app.type.TypeError;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ForbiddenException() {
		super(TypeError.ERR_HTTP_403.msg());
	}

}
