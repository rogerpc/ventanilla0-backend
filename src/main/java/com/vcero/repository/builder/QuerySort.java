package com.vcero.repository.builder;

import javax.validation.constraints.NotEmpty;

public class QuerySort {

	@NotEmpty
	private String key;
	@NotEmpty
	private String order;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
