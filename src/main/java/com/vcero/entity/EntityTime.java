package com.vcero.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@MappedSuperclass
public abstract class EntityTime extends EntityType {

	@JsonProperty(access = Access.READ_ONLY)
	@Column(insertable = true, updatable = false)
	private ZonedDateTime created;
	@JsonIgnore
	private ZonedDateTime updated;

	@PrePersist
	protected void onCreate() {
		ZonedDateTime date = ZonedDateTime.now();
		this.setCreated(date);
		this.setUpdated(date);
	}

	@PreUpdate
	protected void onUpdate() {
		this.setUpdated(ZonedDateTime.now());
	}

	public ZonedDateTime getCreated() {
		return created;
	}

	protected void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public ZonedDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(ZonedDateTime updated) {
		this.updated = updated;
	}

	

}
