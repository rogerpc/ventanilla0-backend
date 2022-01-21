package com.vcero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcero.entity.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

}
