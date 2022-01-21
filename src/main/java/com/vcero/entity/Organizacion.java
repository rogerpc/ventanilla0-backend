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
@Table(name = "t_organizacion")
public class Organizacion extends EntityTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idorg;
	@NotEmpty
	private String nombreorg;
	@NotEmpty
	private String ncortoorg;

	@Override
	public String id() {
		return idorg.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.ORGANIZACION;
	}

	public Integer getIdorg() {
		return idorg;
	}

	public void setIdorg(Integer idorg) {
		this.idorg = idorg;
	}

	public String getNombreorg() {
		return nombreorg;
	}

	public void setNombreorg(String nombreorg) {
		this.nombreorg = nombreorg;
	}

	public String getNcortoorg() {
		return ncortoorg;
	}

	public void setNcortoorg(String ncortoorg) {
		this.ncortoorg = ncortoorg;
	}

}
