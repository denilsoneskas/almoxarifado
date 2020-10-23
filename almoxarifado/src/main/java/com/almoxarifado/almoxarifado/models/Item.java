package com.almoxarifado.almoxarifado.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	private String Nome;
	
	private String aberto;
	
	private String fechado;
	
	private String comprar;
	
	@ManyToOne
	private Setor setor;

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getAberto() {
		return aberto;
	}

	public void setAberto(String aberto) {
		this.aberto = aberto;
	}

	public String getFechado() {
		return fechado;
	}

	public void setFechado(String fechado) {
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
