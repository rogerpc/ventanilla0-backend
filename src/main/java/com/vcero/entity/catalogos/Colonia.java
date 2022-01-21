package com.vcero.entity.catalogos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityType;

@Entity
@Table(name = "c_colonia")
public class Colonia extends EntityType {
	  
	@Id
	private Integer idcolonia;
	private String cpcolonia;
	private String nombrecolonia;
	private String tipocolonia;
	private String ciudadcolonia;
	private String zonacolonia;
	private Integer idmnpio;
	 
	@Override
	public String id() {
		return this.idcolonia.toString();
	}
	
	@Override
	public TypeEntity type() {
		return TypeEntity.COLONIA;
	}

	public Integer getIdcolonia() {
		return idcolonia;
	}

	public void setIdcolonia(Integer idcolonia) {
		this.idcolonia = idcolonia;
	}

	public String getCpcolonia() {
		return cpcolonia;
	}

	public void setCpcolonia(String cpcolonia) {
		this.cpcolonia = cpcolonia;
	}

	public String getNombrecolonia() {
		return nombrecolonia;
	}

	public void setNombrecolonia(String nombrecolonia) {
		this.nombrecolonia = nombrecolonia;
	}

	public String getTipocolonia() {
		return tipocolonia;
	}

	public void setTipocolonia(String tipocolonia) {
		this.tipocolonia = tipocolonia;
	}

	public String getCiudadcolonia() {
		return ciudadcolonia;
	}

	public void setCiudadcolonia(String ciudadcolonia) {
		this.ciudadcolonia = ciudadcolonia;
	}

	public String getZonacolonia() {
		return zonacolonia;
	}

	public void setZonacolonia(String zonacolonia) {
		this.zonacolonia = zonacolonia;
	}

	public Integer getIdmnpio() {
		return idmnpio;
	}

	public void setIdmnpio(Integer idmnpio) {
		this.idmnpio = idmnpio;
	}
	
}
