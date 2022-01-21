package com.vcero.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcero.app.type.TypeEntity;

@Entity
@Table(name = "t_unidad_org")
public class UnidadOrg extends EntityTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idunidad;
	@NotEmpty
	private String nombreunidad;
	@NotNull
	private Integer idorg;

	@Override
	public String id() {
		return idunidad.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.UNIDAD;
	}

	public Integer getIdunidad() {
		return idunidad;
	}

	public void setIdunidad(Integer idunidad) {
		this.idunidad = idunidad;
	}

	public String getNombreunidad() {
		return nombreunidad;
	}

	public void setNombreunidad(String nombreunidad) {
		this.nombreunidad = nombreunidad;
	}

	public Integer getIdorg() {
		return idorg;
	}

	public void setIdorg(Integer idorg) {
		this.idorg = idorg;
	}

}
