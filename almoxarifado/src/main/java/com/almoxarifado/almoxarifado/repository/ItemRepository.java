package com.almoxarifado.almoxarifado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almoxarifado.almoxarifado.models.Item;
import com.almoxarifado.almoxarifado.models.Setor;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Iterable<Item> findBySetor(Setor setor);
	Item findByNome(String nome);

	@Query(nativeQuery = true, value = "SELECT * FROM item INNER JOIN setor ON item.setor_id = setor.id ORDER BY setor.nome ASC, item.nome ASC") 
	List<Item> itensTodos();
	
	@Query(nativeQuery = true, value = "SELECT * FROM item INNER JOIN setor ON item.setor_id = setor.id WHERE item.comprar = 'Sim' ORDER BY setor.nome ASC, item.nome ASC") 
	List<Item> itensComprar();
}
