package com.vcero.repository.builder;

import javax.validation.constraints.NotEmpty;

public class QueryFilter {

	@NotEmpty
	private String key;
	@NotEmpty
	private String operator;
	private String value;
	@NotEmpty
	private String type;

	public QueryFilter() {
	}

	public QueryFilter(String key, String operator, String value, String type) {
		this.key = key;
		this.operator = operator;
		this.value = value;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
