package com.vcero.entity;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@MappedSuperclass
public abstract class EntityStatus extends EntityTime {

	@JsonIgnore
	private ZonedDateTime updstatus;
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean status;

	public void onStatus(Boolean status) {
		this.setUpdstatus(ZonedDateTime.now());
		this.status = status;
	}

	@PrePersist
	protected void onCreate() {
		ZonedDateTime date = ZonedDateTime.now();
		this.setCreated(date);
		this.setUpdated(date);
		this.setUpdstatus(date);
		if (Objects.isNull(this.status)) {
			this.status = true;
		}
	}

	public ZonedDateTime getUpdstatus() {
		return updstatus;
	}

	public void setUpdstatus(ZonedDateTime updstatus) {
		this.updstatus = updstatus;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getStatus() {
		return status;
	}

}
