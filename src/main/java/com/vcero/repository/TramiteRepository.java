package com.vcero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcero.entity.Tramite;

public interface TramiteRepository extends JpaRepository<Tramite, Integer> {

	public boolean existsByCveservicio(String cveservicio);

}
