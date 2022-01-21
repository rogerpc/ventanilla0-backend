package com.vcero.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vcero.entity.Persona;

public class Ciudadano {

	@NotNull
	private Integer codigo;
	@Valid
	@NotNull
	private Persona persona;
	@Valid
	@Size(min = 1)
	private List<CiudadanoDoc> documentacion = new ArrayList<CiudadanoDoc>();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<CiudadanoDoc> getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(List<CiudadanoDoc> documentacion) {
		this.documentacion = documentacion;
	}

}
