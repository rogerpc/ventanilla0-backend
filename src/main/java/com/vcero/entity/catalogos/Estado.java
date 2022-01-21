package com.vcero.entity.catalogos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityType;

@Entity
@Table(name = "c_estado")
public class Estado extends EntityType {

	@Id
	private Integer idedo;
	private String nombreedo;
	private String claveedo;
	private String idpais;
	
	@Override
	public String id() {
		return this.idedo.toString();
	}
	
	@Override
	public TypeEntity type() {
		return TypeEntity.ESTADO;
	}
	
	public Integer getIdedo() {
		return idedo;
	}
	public void setIdedo(Integer idedo) {
		this.idedo = idedo;
	}
	public String getNombreedo() {
		return nombreedo;
	}
	public void setNombreedo(String nombreedo) {
		this.nombreedo = nombreedo;
	}
	public String getClaveedo() {
		return claveedo;
	}
	public void setClaveedo(String claveedo) {
		this.claveedo = claveedo;
	}
	public String getIdpais() {
		return idpais;
	}
	public void setIdpais(String idpais) {
		this.idpais = idpais;
	}
	
	
	
	
}
