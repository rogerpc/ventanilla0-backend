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
@Table(name = "t_validaremail")
public class ValidarEmail extends EntityTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idvalidar;
	@NotEmpty
	private String email;

	@Override
	public String id() {
		return idvalidar.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.VALIDAR_EMAIL;
	}

	public Integer getIdvalidar() {
		return idvalidar;
	}

	public void setIdvalidar(Integer idvalidar) {
		this.idvalidar = idvalidar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
