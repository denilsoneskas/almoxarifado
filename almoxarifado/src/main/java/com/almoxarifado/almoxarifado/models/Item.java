package com.almoxarifado.almoxarifado.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;


@Entity
public class Item {

	@Id
	@NotEmpty
	private String nome;
	
	@PositiveOrZero
	private int aberto;
	
	@PositiveOrZero
	private int fechado;
	
	private String comprar;
	
	@ManyToOne
	private Setor setor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAberto() {
		return aberto;
	}

	public void setAberto(int aberto) {
		this.aberto = aberto;
	}

	public int getFechado() {
		return fechado;
	}

	public void setFechado(int fechado) {
		this.fechado = fechado;
	}

	public String getComprar() {
		return comprar;
	}

	public void setComprar(String comprar) {
		this.comprar = comprar;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}
