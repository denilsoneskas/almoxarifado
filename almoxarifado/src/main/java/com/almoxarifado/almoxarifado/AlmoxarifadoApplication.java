package com.almoxarifado.almoxarifado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AlmoxarifadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmoxarifadoApplication.class, args);
		System.out.println("Crie um banco de dados chamado 'almoxarifado' no PostgreSQL");
		System.out.println("Para criar o usuario, execute a SQL abaixo:");
		System.out.println("INSERT INTO usuario (login, senha) VALUES ('admin', '"+new BCryptPasswordEncoder().encode("123")+"');");
		System.out.println("As credenciais são: admin 123");
	}
}
