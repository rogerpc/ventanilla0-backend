package com.vcero.entity.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityStatus;

@Entity
@Immutable
@Table(name = "vv_tramite")
public class VTramite extends EntityStatus {

	@Id
	private Integer idtramite;
	private String nombretramite;
	private String desctramite;
	private Integer resolucion;
	private String cveservicio;
	private Boolean subsanar;
	private Integer idorg;
	private String nombreorg;
	private String ncortoorg;
	private Integer idunidad;
	private String nombreunidad;

	@Override
	public String id() {
		return idtramite.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.REQUISITOS;
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

	public Boolean getSubsanar() {
		return subsanar;
	}

	public void setSubsanar(Boolean subsanar) {
		this.subsanar = subsanar;
	}

	public Integer getIdorg() {
		return idorg;
	}

	public void setIdorg(Integer idorg) {
		this.idorg = idorg;
	}

	public String getNombreorg() {
		return nombreorg;
	}

	public void setNombreorg(String nombreorg) {
		this.nombreorg = nombreorg;
	}

	public String getNcortoorg() {
		return ncortoorg;
	}

	public void setNcortoorg(String ncortoorg) {
		this.ncortoorg = ncortoorg;
	}

	public Integer getIdunidad() {
		return idunidad;
	}

	public void setIdunidad(Integer idunidad) {
		this.idunidad = idunidad;
	}

	public String getNombreunidad() {
		return nombreunidad;
	}

	public void setNombreunidad(String nombreunidad) {
		this.nombreunidad = nombreunidad;
	}

}
