package com.vcero.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcero.app.type.TypeEntity;

@Entity
@Table(name = "d_requisitos")
public class Requisitos extends EntityTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idrequisito;
	@NotNull
	private Integer iddoc;
	@NotNull
	private Boolean requerido;

	@ManyToOne
	@JoinColumn(name = "idtramite")
	@JsonIgnoreProperties(value = { "requisitos" })
	private Tramite tramite;

	@Override
	public String id() {
		return idrequisito.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.REQUISITOS;
	}

	public Integer getIdrequisito() {
		return idrequisito;
	}

	public void setIdrequisito(Integer idrequisito) {
		this.idrequisito = idrequisito;
	}

	public Integer getIddoc() {
		return iddoc;
	}

	public void setIddoc(Integer iddoc) {
		this.iddoc = iddoc;
	}

	public Boolean getRequerido() {
		return requerido;
	}

	public void setRequerido(Boolean requerido) {
		this.requerido = requerido;
	}

	public Tramite getTramite() {
		return tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

}
