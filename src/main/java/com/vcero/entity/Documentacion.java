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
@Table(name = "d_documentacion")
public class Documentacion extends EntityType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer iddoctcion;
	@NotNull
	private Integer iddoc;
	@NotNull
	private Boolean requerido;
	private String nombrefile;
	@NotNull
	private Boolean docvalido;

	@ManyToOne
	@JoinColumn(name = "idtramiteper")
	@JsonIgnoreProperties(value = { "documentacion" })
	private TramitePersona tramite;

	public Documentacion() {
	}

	public Documentacion(@NotNull Integer iddoc, @NotNull Boolean requerido, @NotNull Boolean docvalido) {
		this.iddoc = iddoc;
		this.requerido = requerido;
		this.docvalido = docvalido;
	}


	@Override
	public String id() {
		return iddoctcion.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.DOCUMENTACION;
	}

	public Integer getIddoctcion() {
		return iddoctcion;
	}

	public void setIddoctcion(Integer iddoctcion) {
		this.iddoctcion = iddoctcion;
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

	public String getNombrefile() {
		return nombrefile;
	}

	public void setNombrefile(String nombrefile) {
		this.nombrefile = nombrefile;
	}

	public TramitePersona getTramite() {
		return tramite;
	}

	public void setTramite(TramitePersona tramite) {
		this.tramite = tramite;
	}

	public Boolean getDocvalido() {
		return docvalido;
	}

	public void setDocvalido(Boolean docvalido) {
		this.docvalido = docvalido;
	}

}
