package com.almoxarifado.almoxarifado.repository;

import org.springframework.data.repository.CrudRepository;

import com.almoxarifado.almoxarifado.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
}
