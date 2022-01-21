package com.vcero.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Validar {

	@NotNull
	private Integer idedotramite;
	@Valid
	@Size(min = 1)
	private List<ValidarDoc> documentacion = new ArrayList<ValidarDoc>();

	public Integer getIdedotramite() {
		return idedotramite;
	}

	public void setIdedotramite(Integer idedotramite) {
		this.idedotramite = idedotramite;
	}

	public List<ValidarDoc> getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(List<ValidarDoc> documentacion) {
		this.documentacion = documentacion;
	}

}
