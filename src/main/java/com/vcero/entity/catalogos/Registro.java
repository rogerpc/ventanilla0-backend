package com.vcero.entity.catalogos;

import javax.persistence.Id;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityType;

public class Registro extends EntityType {

	@Id
	private Integer idregistro;
	private String nombreregistro;
	private String descregistro;

	@Override
	public String id() {
		return idregistro.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.REGISTRO;
	}

	public Integer getIdregistro() {
		return idregistro;
	}

	public void setIdregistro(Integer idregistro) {
		this.idregistro = idregistro;
	}

	public String getNombreregistro() {
		return nombreregistro;
	}

	public void setNombreregistro(String nombreregistro) {
		this.nombreregistro = nombreregistro;
	}

	public String getDescregistro() {
		return descregistro;
	}

	public void setDescregistro(String descregistro) {
		this.descregistro = descregistro;
	}

	public enum R {

		ONLINE(1), OAC(2);

		private Integer id;

		R(int id) {
			this.id = id;
		}

		public Integer id() {
			return this.id;
		}
	}

}
