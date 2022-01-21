package com.vcero.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.vcero.app.AppRegExp;
import com.vcero.app.type.TypeEntity;

@Entity
@Table(name = "t_persona")
public class Persona extends EntityTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idpersona;
	@NotEmpty
	private String nombrepersona;
	@NotEmpty
	private String apaternopersona;
	private String amaternopersona;
	@NotEmpty
	private String celularpersona;
	@NotEmpty
	private String telefonopersona;
	@NotEmpty
	@Pattern(regexp = AppRegExp.EMAIL, message = AppRegExp.EMAIL_MSG)
	private String emailpersona;
	@NotEmpty
	@Pattern(regexp = AppRegExp.RFC, message = AppRegExp.RFC_MSG)
	private String rfcpersona;
	@NotEmpty
	@Pattern(regexp = AppRegExp.CURP, message = AppRegExp.CURP_MSG)
	private String curppersona;
	@NotNull
	private ZonedDateTime fnacimientopersona;
	@NotEmpty
	private String calledomicilio;
	@NotEmpty
	private String numextdomicilio;
	private String numintdomicilio;	
	@NotNull
	private Integer idcolonia;
	  
	@Override
	public String id() {
		return idpersona.toString();
	}

	@Override
	public TypeEntity type() {
		return TypeEntity.PERSONA;
	}

	public Integer getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(Integer idpersona) {
		this.idpersona = idpersona;
	}

	public String getNombrepersona() {
		return nombrepersona;
	}

	public void setNombrepersona(String nombrepersona) {
		this.nombrepersona = nombrepersona;
	}

	public String getApaternopersona() {
		return apaternopersona;
	}

	public void setApaternopersona(String apaternopersona) {
		this.apaternopersona = apaternopersona;
	}

	public String getAmaternopersona() {
		return amaternopersona;
	}

	public void setAmaternopersona(String amaternopersona) {
		this.amaternopersona = amaternopersona;
	}

	public String getCelularpersona() {
		return celularpersona;
	}

	public void setCelularpersona(String celularpersona) {
		this.celularpersona = celularpersona;
	}

	public String getTelefonopersona() {
		return telefonopersona;
	}

	public void setTelefonopersona(String telefonopersona) {
		this.telefonopersona = telefonopersona;
	}

	public String getEmailpersona() {
		return emailpersona;
	}

	public void setEmailpersona(String emailpersona) {
		this.emailpersona = emailpersona;
	}

	public String getRfcpersona() {
		return rfcpersona;
	}

	public void setRfcpersona(String rfcpersona) {
		this.rfcpersona = rfcpersona;
	}

	public String getCurppersona() {
		return curppersona;
	}

	public void setCurppersona(String curppersona) {
		this.curppersona = curppersona;
	}

	public ZonedDateTime getFnacimientopersona() {
		return fnacimientopersona;
	}

	public void setFnacimientopersona(ZonedDateTime fnacimientopersona) {
		this.fnacimientopersona = fnacimientopersona;
	}

	public String getCalledomicilio() {
		return calledomicilio;
	}

	public void setCalledomicilio(String calledomicilio) {
		this.calledomicilio = calledomicilio;
	}

	public String getNumextdomicilio() {
		return numextdomicilio;
	}

	public void setNumextdomicilio(String numextdomicilio) {
		this.numextdomicilio = numextdomicilio;
	}

	public String getNumintdomicilio() {
		return numintdomicilio;
	}

	public void setNumintdomicilio(String numintdomicilio) {
		this.numintdomicilio = numintdomicilio;
	}

	public Integer getIdcolonia() {
		return idcolonia;
	}

	public void setIdcolonia(Integer idcolonia) {
		this.idcolonia = idcolonia;
	}
	
}
