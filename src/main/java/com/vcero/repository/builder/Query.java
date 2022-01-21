package com.vcero.repository.builder;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

public class Query {

	@Min(1)
	private Integer page = 1;
	@Min(1)
	private Integer limit = 100;
	@Valid
	private List<QueryFilter> filters = new ArrayList<QueryFilter>();
	@Valid
	private List<QuerySort> sorts = new ArrayList<QuerySort>();

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<QueryFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<QueryFilter> filters) {
		this.filters = filters;
	}

	public List<QuerySort> getSorts() {
		return sorts;
	}

	public void setSorts(List<QuerySort> sorts) {
		this.sorts = sorts;
	}

	public void addFilter(String key, String operator, String value, String type) {
		this.filters.add(new QueryFilter(key, operator, value, type));
	}

}
