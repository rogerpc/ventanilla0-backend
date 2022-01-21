package com.vcero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcero.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

	public Optional<Persona> findByRfcpersona(String rfcpersona);

	public Optional<Persona> findByCurppersona(String curppersona);

	public boolean existsByRfcpersona(String rfcpersona);

	public boolean existsByCurppersona(String curppersona);

}
