package com.vcero.entity.catalogos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityType;

@Entity
@Table(name = "c_edo_tramite")
public class EdoTramite extends EntityType {

	@Id
	private Integer idedotramite;
	private String nombreedotramite;
	private String descedotramite;

	@Override
	public String id() {
		return idedotramite.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.EDO_TRAMITE;
	}

	public Integer getIdedotramite() {
		return idedotramite;
	}

	public void setIdedotramite(Integer idedotramite) {
		this.idedotramite = idedotramite;
	}

	public String getNombreedotramite() {
		return nombreedotramite;
	}

	public void setNombreedotramite(String nombreedotramite) {
		this.nombreedotramite = nombreedotramite;
	}

	public String getDescedotramite() {
		return descedotramite;
	}

	public void setDescedotramite(String descedotramite) {
		this.descedotramite = descedotramite;
	}

	public enum R {

		SOLICITUD(1), SUBSANAR(2), AUTORIZADO(3), DENEGADO(4);

		private Integer id;

		R(int id) {
			this.id = id;
		}

		public Integer id() {
			return this.id;
		}
	}

}
