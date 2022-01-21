package com.vcero.app.response;

import java.time.ZonedDateTime;
import java.util.Collections;

import com.vcero.repository.builder.QData;

public class HttpOk {

	private ZonedDateTime timestamp = ZonedDateTime.now();
	private String message = "";
	private Object detail;

	public HttpOk(String message, Object detail) {
		this.message = message;
		if (detail instanceof QData) {
			this.detail = detail;
		} else {
			this.detail = Collections.singletonMap("data", detail);
		}
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public Object getDetail() {
		return detail;
	}

}
