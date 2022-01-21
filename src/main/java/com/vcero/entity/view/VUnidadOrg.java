package com.vcero.entity.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityTime;

@Entity
@Immutable
@Table(name = "vv_unidadorg")
public class VUnidadOrg extends EntityTime {

	@Id
	private Integer idunidad;
	private Integer idorg;
	private String nombreorg;
	private String nombreunidad;

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

	public String getNombreunidad() {
		return nombreunidad;
	}

	public void setNombreunidad(String nombreunidad) {
		this.nombreunidad = nombreunidad;
	}

}
