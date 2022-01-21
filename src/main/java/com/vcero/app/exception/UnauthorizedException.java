package com.vcero.app.exception;

import com.vcero.app.type.TypeError;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
		super(TypeError.ERR_HTTP_401.msg());
	}

}