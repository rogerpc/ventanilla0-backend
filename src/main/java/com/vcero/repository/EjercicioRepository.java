package com.vcero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vcero.entity.catalogos.Ejercicio;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE c_ejercicio SET status = FALSE, updstatus = CURRENT_TIMESTAMP WHERE status = TRUE", nativeQuery = true)
	public int updateAllStatusFalse();

	public Optional<Ejercicio> findFirstByStatusTrueOrderByIdejercicioDesc();

}
