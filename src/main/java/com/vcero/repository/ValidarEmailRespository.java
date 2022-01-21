package com.vcero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vcero.entity.ValidarEmail;

public interface ValidarEmailRespository extends JpaRepository<ValidarEmail, Integer> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM t_validaremail WHERE email = :email", nativeQuery = true)
	public int deleteByEmail(@Param("email") String email);

}
