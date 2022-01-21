package com.vcero.entity.catalogos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityStatus;

@Entity
@Table(name = "c_ejercicio")
public class Ejercicio extends EntityStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idejercicio;
	@NotNull
	private Integer anioejercicio;

	@Override
	public String id() {
		return anioejercicio.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.EJERCICIO;
	}

	public Integer getIdejercicio() {
		return idejercicio;
	}

	public void setIdejercicio(Integer idejercicio) {
		this.idejercicio = idejercicio;
	}

	public Integer getAnioejercicio() {
		return anioejercicio;
	}

	public void setAnioejercicio(Integer anioejercicio) {
		this.anioejercicio = anioejercicio;
	}

}
