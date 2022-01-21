package com.vcero.entity.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityType;

@Entity
@Immutable
@Table(name = "vv_cp")
public class VCodigoPostal extends EntityType {

	@Id
	private String cpcolonia;
	private String nombremnpio;
	private String nombreedo;
	private String nombrepais;
	
	@Override
	public String id() {
		return this.cpcolonia;
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.CODIGO_POSTAL;
	}

	public String getCpcolonia() {
		return cpcolonia;
	}

	public void setCpcolonia(String cpcolonia) {
		this.cpcolonia = cpcolonia;
	}

	public String getNombremnpio() {
		return nombremnpio;
	}

	public void setNombremnpio(String nombremnpio) {
		this.nombremnpio = nombremnpio;
	}

	public String getNombreedo() {
		return nombreedo;
	}

	public void setNombreedo(String nombreedo) {
		this.nombreedo = nombreedo;
	}

	public String getNombrepais() {
		return nombrepais;
	}

	public void setNombrepais(String nombrepais) {
		this.nombrepais = nombrepais;
	}

}
