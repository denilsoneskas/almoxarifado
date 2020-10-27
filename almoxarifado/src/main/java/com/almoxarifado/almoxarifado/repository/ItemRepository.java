package com.almoxarifado.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almoxarifado.almoxarifado.models.Item;
import com.almoxarifado.almoxarifado.models.Setor;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Iterable<Item> findBySetor(Setor setor);
	Item findByNome(String nome);
}
