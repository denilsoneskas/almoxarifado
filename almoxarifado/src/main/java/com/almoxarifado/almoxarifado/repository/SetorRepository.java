package com.almoxarifado.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almoxarifado.almoxarifado.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {
	
	Setor findByCodigo(long codigo);

}
