package com.vcero.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcero.app.type.TypeEntity;

@Entity
@Table(name = "d_documento")
public class Documento extends EntityTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer iddoc;
	@NotEmpty
	private String nombredoc;
	@NotEmpty
	private String descdoc;

	@Override
	public String id() {
		return iddoc.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.DOCUMENTO;
	}

	public Integer getIddoc() {
		return iddoc;
	}

	public void setIddoc(Integer iddoc) {
		this.iddoc = iddoc;
	}

	public String getNombredoc() {
		return nombredoc;
	}

	public void setNombredoc(String nombredoc) {
		this.nombredoc = nombredoc;
	}

	public String getDescdoc() {
		return descdoc;
	}

	public void setDescdoc(String descdoc) {
		this.descdoc = descdoc;
	}

}
