package com.vcero.entity.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityTime;

@Entity
@Immutable
@Table(name = "vv_requisitos")
public class VRequisitos extends EntityTime {

	@Id
	private Integer idrequisito;
	private Integer idtramite;
	private String nombretramite;
	private String cveservicio;
	private Integer iddoc;
	private String nombredoc;
	private Boolean requerido;

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

	public String getCveservicio() {
		return cveservicio;
	}

	public void setCveservicio(String cveservicio) {
		this.cveservicio = cveservicio;
	}

	public Integer getIddoc() {
		return iddoc;
	}

	public void setIddoc(Integer iddoc) {
		this.iddoc = iddoc;
	}

	public String getNombredoc() {
		return nombredoc;
	}

	public void setNombredoc(String nombredoc) {
		this.nombredoc = nombredoc;
	}

	public Boolean getRequerido() {
		return requerido;
	}

	public void setRequerido(Boolean requerido) {
		this.requerido = requerido;
	}

}
