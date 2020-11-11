package com.almoxarifado.almoxarifado.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class AlmoxarifadoController {
	
	@Autowired
	SetorRepository setorRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/cadastrarSetor", method=RequestMethod.GET)
	public String form() {
		return "setor/formSetor";
	}

	@RequestMapping(value="/setores", method=RequestMethod.POST)
	public String form(@Valid Setor setor, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/setores";
		}
		setorRepository.save(setor);
		attributes.addFlashAttribute("mensagem", "Setor salvo com sucesso!");
		return "redirect:/setores";
	}
	
	@RequestMapping(value="/setores", method=RequestMethod.GET)
	public ModelAndView listaSetores() {
		ModelAndView mv = new ModelAndView("setor/setores");
		Iterable<Setor> setores = setorRepository.findAll();
		mv.addObject("setores", setores);
		return mv;
	}
	
	@RequestMapping(value="/detalhesSetor/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesSetor(@PathVariable("id") long id) {
		Setor setor = setorRepository.findById(id).get();
		ModelAndView mv = new ModelAndView("setor/detalhesSetor");
		mv.addObject("setor", setor);
		Iterable<Item> itens = itemRepository.findBySetor(setor);
		mv.addObject("itens", itens);
		return mv;		
	}
	
	@RequestMapping(value="/detalhesSetor/{id}", method=RequestMethod.POST)
	public String detalhesSetor(@PathVariable("id") long id, @Valid Item item, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalhesSetor/{id}";
		}
		Setor setor = setorRepository.findById(id).get();
		item.setSetor(setor);
		
		itemRepository.save(item);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return "redirect:/detalhesSetor/{id}";		
	}
	
	@RequestMapping("/deletar")
	public String deletarSetor(long id) {
		Setor setor = setorRepository.findById(id).get();
		setorRepository.delete(setor);
		return "redirect:/setores";
	}

	@RequestMapping("/deletarItem")
	public String deletarItem(long id) {
		Item item = itemRepository.findById(id).get();
		itemRepository.delete(item);
		
		Setor setor = item.getSetor();
		long idLong = setor.getId();
		String codigo = "" + idLong;
		
		return "redirect:/detalhesSetor/" + codigo;
	}
	
	@RequestMapping(value="/itensComprar", method=RequestMethod.GET)
	public ModelAndView itensComprar() {
		ModelAndView mv = new ModelAndView("item/itensComprar");
		List<Item> itensComprar = itemRepository.itensComprar();
		mv.addObject("itensComprar", itensComprar);
		return mv;
	}
	
	@RequestMapping("/atualizarItem")
	public String atualizarItem(long id, String comprar, String acao ) {
		Item item = itemRepository.findById(id).get();

		if (acao.equals("mais")) {
			int qtd = item.getFechado() + 1;
			item.setFechado(qtd);
		}
		if (acao.equals("menos")) {
			int qtd = item.getFechado() - 1;
			item.setFechado(qtd);
		}
		
		if (item.getFechado() <= 0 ) {
			item.setFechado(0);
			item.setComprar("Sim");
		} else {
			item.setComprar("Não");
		}

		itemRepository.save(item);
		return "redirect:/itensComprar";
	}

}
