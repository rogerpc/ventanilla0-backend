package com.vcero.model;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class CiudadanoDoc {

	@NotNull
	private Integer iddoc;
	@NotNull
	private MultipartFile filedoc;

	public Integer getIddoc() {
		return iddoc;
	}

	public void setIddoc(Integer iddoc) {
		this.iddoc = iddoc;
	}

	public MultipartFile getFiledoc() {
		return filedoc;
	}

	public void setFiledoc(MultipartFile filedoc) {
		this.filedoc = filedoc;
	}

}
