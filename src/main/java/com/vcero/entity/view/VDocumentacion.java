package com.vcero.entity.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.vcero.app.type.TypeEntity;
import com.vcero.entity.EntityType;

@Entity
@Immutable
@Table(name = "vv_documentacion")
public class VDocumentacion extends EntityType {

	@Id
	private Integer iddoctcion;
	private Integer idtramiteper;
	private Integer iddoc;
	private String nombredoc;
	private Boolean requerido;
	private String nombrefile;
	private Integer idpersona;
	private Integer idtramite;
	private Boolean docvalido;

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

	public Integer getIdtramiteper() {
		return idtramiteper;
	}

	public void setIdtramiteper(Integer idtramiteper) {
		this.idtramiteper = idtramiteper;
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

	public String getNombrefile() {
		return nombrefile;
	}

	public void setNombrefile(String nombrefile) {
		this.nombrefile = nombrefile;
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

	public Boolean getDocvalido() {
		return docvalido;
	}

	public void setDocvalido(Boolean docvalido) {
		this.docvalido = docvalido;
	}

}
