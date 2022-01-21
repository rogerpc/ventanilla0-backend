package com.vcero.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcero.app.type.TypeEntity;

@Entity
@Table(name = "d_tramite_persona")
public class TramitePersona extends EntityTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idtramiteper;
	@NotNull
	private Integer idpersona;
	@NotNull
	private Integer idtramite;
	@NotNull
	private Integer idedotramite;
	@NotNull
	private Integer idregistro;
	@NotNull
	private Integer idejercicio;
	@JsonProperty(access = Access.READ_ONLY)
	private Integer consecutivo;

	@Valid
	@Size(min = 1)
	@OneToMany(mappedBy = "tramite", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "tramite" })
	private List<Documentacion> documentacion = new ArrayList<Documentacion>();

	@Override
	public String id() {
		return idtramiteper.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.TRAMITE_PERSPONA;
	}

	public Integer getIdtramiteper() {
		return idtramiteper;
	}

	public void setIdtramiteper(Integer idtramiteper) {
		this.idtramiteper = idtramiteper;
	}

	public Integer getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(Integer idpersona) {
		this.idpersona = idpersona;
	}

	public Integer getIdtramite() {
		return idtramite;
	}

	public void setIdtramite(Integer idtramite) {
		this.idtramite = idtramite;
	}

	public Integer getIdedotramite() {
		return idedotramite;
	}

	public void setIdedotramite(Integer idedotramite) {
		this.idedotramite = idedotramite;
	}

	public Integer getIdregistro() {
		return idregistro;
	}

	public void setIdregistro(Integer idregistro) {
		this.idregistro = idregistro;
	}

	public Integer getIdejercicio() {
		return idejercicio;
	}

	public void setIdejercicio(Integer idejercicio) {
		this.idejercicio = idejercicio;
	}

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public List<Documentacion> getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(List<Documentacion> documentacion) {
		this.documentacion.clear();
		documentacion.forEach(this::addDocumentacion);
	}

	public void addDocumentacion(Documentacion documentacion) {
		documentacion.setTramite(this);
		this.documentacion.add(documentacion);
	}

	public void removeDocumentacion(Documentacion documentacion) {
		documentacion.setTramite(null);
		this.documentacion.remove(documentacion);
	}

}
