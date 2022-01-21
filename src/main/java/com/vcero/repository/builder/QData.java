package com.vcero.repository.builder;

import java.util.ArrayList;
import java.util.List;

public class QData<T> {

	private List<T> data = new ArrayList<T>();
	private Long totalItems = 0L;
	private Integer totalPages = 0;
	private QDataPage currentPage = new QDataPage();

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public QDataPage getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(QDataPage currentPage) {
		this.currentPage = currentPage;
	}

}
