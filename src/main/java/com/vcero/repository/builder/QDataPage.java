package com.vcero.repository.builder;

public class QDataPage {

	private Integer pageNumber = 0;
	private Integer fromItem = 0;
	private Integer toItem = 0;
	private Integer itemsPage = 0;
	private Integer itemsPerPage = 0;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getFromItem() {
		return fromItem;
	}

	public void setFromItem(Integer fromItem) {
		this.fromItem = fromItem;
	}

	public Integer getToItem() {
		return toItem;
	}

	public void setToItem(Integer toItem) {
		this.toItem = toItem;
	}

	public Integer getItemsPage() {
		return itemsPage;
	}

	public void setItemsPage(Integer itemsPage) {
		this.itemsPage = itemsPage;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

}
