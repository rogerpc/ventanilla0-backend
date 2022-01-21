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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcero.app.type.TypeEntity;

@Entity
@Table(name = "d_tramite")
public class Tramite extends EntityStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idtramite;
	@NotEmpty
	private String nombretramite;
	@NotEmpty
	private String desctramite;
	@NotNull
	private Integer resolucion;
	@NotEmpty
	private String cveservicio;
	@NotNull
	private Integer idunidad;
	@JsonProperty(access = Access.READ_ONLY)
	private Integer secuencia;
	@NotNull
	private Boolean subsanar;

	@Valid
	@Size(min = 1)
	@OneToMany(mappedBy = "tramite", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "tramite" })
	private List<Requisitos> requisitos = new ArrayList<Requisitos>();

	@Override
	public String id() {
		return idtramite.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.TRAMITE;
	}

	public Integer getIdtramite() {
		return idtramite;
	}

	public void setIdtramite(Integer idtramite) {
		this.idtramite = idtramite;
	}

	public String getNombretramite() {
		return nombretramite;
	}

	public void setNombretramite(String nombretramite) {
		this.nombretramite = nombretramite;
	}

	public String getDesctramite() {
		return desctramite;
	}

	public void setDesctramite(String desctramite) {
		this.desctramite = desctramite;
	}

	public Integer getResolucion() {
		return resolucion;
	}

	public void setResolucion(Integer resolucion) {
		this.resolucion = resolucion;
	}

	public String getCveservicio() {
		return cveservicio;
	}

	public void setCveservicio(String cveservicio) {
		this.cveservicio = cveservicio;
	}

	public Integer getIdunidad() {
		return idunidad;
	}

	public void setIdunidad(Integer idunidad) {
		this.idunidad = idunidad;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public List<Requisitos> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisitos> requisitos) {
		this.requisitos.clear();
		requisitos.forEach(this::addRequisito);

	}

	public void addRequisito(Requisitos requisito) {
		requisito.setTramite(this);
		this.requisitos.add(requisito);
	}

	public void removeRequisito(Requisitos requisito) {
		requisito.setTramite(null);
		this.requisitos.remove(requisito);
	}

	public Boolean getSubsanar() {
		return subsanar;
	}

	public void setSubsanar(Boolean subsanar) {
		this.subsanar = subsanar;
	}

}
