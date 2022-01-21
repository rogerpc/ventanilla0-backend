package com.vcero.entity.catalogos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityType;

@Entity
@Table(name = "c_municipio")
public class Municipio extends EntityType {
	
	@Id
	private Integer idmnpio;
	private String nombremnpio;
	private String clavemnpio;
	private String idedo;

	@Override
	public String id() {
		return this.idmnpio.toString();
	}
	
	@Override
	public TypeEntity type() {
		return TypeEntity.MUNICIPIO;
	}

	public Integer getIdmnpio() {
		return idmnpio;
	}

	public void setIdmnpio(Integer idmnpio) {
		this.idmnpio = idmnpio;
	}

	public String getNombremnpio() {
		return nombremnpio;
	}

	public void setNombremnpio(String nombremnpio) {
		this.nombremnpio = nombremnpio;
	}

	public String getClavemnpio() {
		return clavemnpio;
	}

	public void setClavemnpio(String clavemnpio) {
		this.clavemnpio = clavemnpio;
	}

	public String getIdedo() {
		return idedo;
	}

	public void setIdedo(String idedo) {
		this.idedo = idedo;
	}	

}
