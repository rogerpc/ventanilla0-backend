package com.vcero.entity.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityTime;

@Entity
@Immutable
@Table(name = "vv_tramite_per")
public class VTramitePersona extends EntityTime {

	@Id
	private Integer idtramiteper;
	private Integer idpersona;
	private String nomcompletoempleado;
	private Integer idtramite;
	private String nombretramite;
	private Integer idedotramite;
	private String nombreedotramite;
	private String solicitud;
	private String expediente;
	private String plazotramite;
	private Integer idregistro;
	private String nombreregistro;
	private Integer idejercicio;
	private String anioejercicio;
	private Integer idorg;
	private String nombreorg;
	private Integer idunidad;
	private String nombreunidad;

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

	public String getNomcompletoempleado() {
		return nomcompletoempleado;
	}

	public void setNomcompletoempleado(String nomcompletoempleado) {
		this.nomcompletoempleado = nomcompletoempleado;
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

	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getPlazotramite() {
		return plazotramite;
	}

	public void setPlazotramite(String plazotramite) {
		this.plazotramite = plazotramite;
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

	public Integer getIdejercicio() {
		return idejercicio;
	}

	public void setIdejercicio(Integer idejercicio) {
		this.idejercicio = idejercicio;
	}

	public String getAnioejercicio() {
		return anioejercicio;
	}

	public void setAnioejercicio(String anioejercicio) {
		this.anioejercicio = anioejercicio;
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
