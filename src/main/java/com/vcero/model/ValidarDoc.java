package com.vcero.model;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ValidarDoc {

	@NotNull
	private Integer iddoc;
	@NotNull
	private Boolean docvalido = false;
	private MultipartFile filedoc;

	public Integer getIddoc() {
		return iddoc;
	}

	public void setIddoc(Integer iddoc) {
		this.iddoc = iddoc;
	}

	public Boolean getDocvalido() {
		return docvalido;
	}

	public void setDocvalido(Boolean docvalido) {
		this.docvalido = docvalido;
	}

	public MultipartFile getFiledoc() {
		return filedoc;
	}

	public void setFiledoc(MultipartFile filedoc) {
		this.filedoc = filedoc;
	}

}
