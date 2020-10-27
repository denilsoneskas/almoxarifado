package com.almoxarifado.almoxarifado.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String form(@Valid Setor setor, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarSetor";
		}
		setorRepository.save(setor);
		attributes.addFlashAttribute("mensagem", "Setor criado com sucesso!");
		return "redirect:/cadastrarSetor";
	}
	
	@RequestMapping(value="/setores", method=RequestMethod.GET)
	public ModelAndView listaSetores() {
		ModelAndView mv = new ModelAndView("setor/setores");
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
	public String detalhesSetor(@PathVariable("codigo") long codigo, @Valid Item item, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Setor setor = setorRepository.findByCodigo(codigo);
		item.setSetor(setor);
		itemRepository.save(item);
		attributes.addFlashAttribute("mensagem", "Item criado com sucesso!");
		return "redirect:/{codigo}";		
	}
	
	@RequestMapping("/deletar")
	public String deletarSetor(long codigo) {
		Setor setor = setorRepository.findByCodigo(codigo);
		setorRepository.delete(setor);
		return "redirect:/setores";
	}

	@RequestMapping("/deletarItem")
	public String deletarItem(String nome) {
		Item item = itemRepository.findByNome(nome);
		itemRepository.delete(item);
		
		Setor setor = item.getSetor();
		long codigoLong = setor.getCodigo();
		String codigo = "" + codigoLong;
		
		return "redirect:/" + codigo;
	}
}
