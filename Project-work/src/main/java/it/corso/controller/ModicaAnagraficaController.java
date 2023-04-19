package it.corso.controller;

import java.util.List;

import it.corso.service.ProfiloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Anagrafica;
import it.corso.service.AnagraficaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/modificaanagrafica")
public class ModicaAnagraficaController {
	
	@Autowired
	private AnagraficaService anagraficaService;

	@Autowired
	private ProfiloService profiloService;
	
	@GetMapping
	public String getPage(Model model) {
		
		List<Anagrafica> anagrafiche = anagraficaService.getAnagrafiche();
		model.addAttribute("anagrafiche",anagrafiche);
		return "ModificaAnagrafica";
		
		
	}

	@GetMapping("/cancellaanagrafica")
	public String cancellaAnagrafica(@RequestParam("id") int id) {
		Anagrafica anagrafica = anagraficaService.getAnagraficaByid(id);
		anagraficaService.cancellaAnagrafica(anagrafica);
		return "redirect:/modificaanagrafica";
	}
	
	
	
	
	
}