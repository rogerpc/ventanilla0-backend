package com.vcero.app.response;

import java.time.ZonedDateTime;
import java.util.HashMap;

public class HttpError {

	private ZonedDateTime timestamp = ZonedDateTime.now();
	private String message = "";
	private Object detail = new HashMap<>();

	private Integer status;
	private String error = "";
	private String path = "";

	public HttpError(Integer status, String error, String message, String path, Object detail) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.detail = detail;
		this.path = path;
	}

	public HttpError(Integer status, String error, String message, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
