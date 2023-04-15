package it.corso.controller;

import java.util.List;

import it.corso.model.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Anagrafica;
import it.corso.model.Prenotazione;
import it.corso.model.Profilo;
import it.corso.service.AnagraficaService;
import it.corso.service.ProfiloService;



@Controller
@RequestMapping("/formanagrafica")
public class FormAnagraficaController {
	
	@Autowired
	private AnagraficaService anagraficaService; 
	
	private ProfiloService profiloService; 
	
	private Profilo profilo; 
	private Anagrafica anagrafica;
	
	@GetMapping
	public String getPage(Model model,
			@RequestParam (name="id", required =false) Integer id) 
	{
		
		anagrafica = (id == null) ? new Anagrafica() : 
			anagraficaService.getAnagraficaByid(id);
		
		List<Anagrafica> anagrafiche = anagraficaService.getAnagrafiche();
		List<Profilo> profili = profiloService.getProfili();
		model.addAttribute("anagrafiche",anagrafiche);
		model.addAttribute("anagrafica",anagrafica);
		model.addAttribute("profili", profili);
		model.addAttribute("profilo", profilo);


		return "formanagrafica" ;
	}
		
	@PostMapping("/salvaanagrafica")
	public String registraAnagrafica(
			@RequestParam ("id") Integer id,
			@RequestParam ("nome") String nome,
			@RequestParam ("cognome") String cognome,
			@RequestParam ("telefono") String telefono, 
			@RequestParam ("profilo") Profilo profilo,
			@RequestParam (name = "prenotazione", required = false)
			Prenotazione prenotazione) {
	     	
        anagraficaService.registraAnagrafica(id, nome, cognome, telefono, profilo);
		return "redirect:/modificaanagrafica";
		}

	@GetMapping("/cancellaanagrafica")
	public String cancellaAnagrafica(@RequestParam("id") int id) {
		Anagrafica anagrafica = anagraficaService.getAnagraficaByid(id);
		anagraficaService.cancellaAnagrafica(anagrafica);
		return "redirect:/modificaanagrafica";
	}
	
	
	}
		
	
	
	
	
	
	
	
	
	
	
	


