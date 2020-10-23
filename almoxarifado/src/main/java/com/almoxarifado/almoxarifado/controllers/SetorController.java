package com.almoxarifado.almoxarifado.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.almoxarifado.almoxarifado.models.Item;
import com.almoxarifado.almoxarifado.models.Setor;
import com.almoxarifado.almoxarifado.repository.ItemRepository;
import com.almoxarifado.almoxarifado.repository.SetorRepository;

@Controller
public class SetorController {
	
	@Autowired
	SetorRepository setorRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@RequestMapping(value="/cadastrarSetor", method=RequestMethod.GET)
	public String form() {
		return "setor/formSetor";
	}
	
	@RequestMapping(value="/cadastrarSetor", method=RequestMethod.POST)
	public String form(Setor setor) {
		setorRepository.save(setor);
		return "redirect:setores";
	}
	
	
	@RequestMapping(value="/setores", method=RequestMethod.GET)
	public ModelAndView listaSetores() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Setor> setores = setorRepository.findAll();
		mv.addObject("setores", setores);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesSetor(@PathVariable("codigo") long codigo) {
		Setor setor = setorRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("setor/detalhesSetor");
		mv.addObject("setor", setor);
		Iterable<Item> itens = itemRepository.findBySetor(setor);
		mv.addObject("itens", itens);
		return mv;		
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesSetor(@PathVariable("codigo") long codigo, Item item) {
		Setor setor = setorRepository.findByCodigo(codigo);
		item.setSetor(setor);
		itemRepository.save(item);
		return "redirect:/{codigo}";		
	}
}
