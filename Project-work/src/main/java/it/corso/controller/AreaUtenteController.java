package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/areautente")
public class AreaUtenteController {
	
	@GetMapping ("/accesso")
	public String getPage() {
		
		
		//hhttp session primo controllo 
		// se session la chiave utente == null rimando al login o hp 
		// altrimenti dichiaro oggetto di tipo anagrafica = 
		//model e attributo 
		//aggiungere la vista dei dati profilo 
		//aggiungere la vista delle prenotazioni 
		return "AreaProfilo";
	}
		
	
	@GetMapping ("/registrazione")
	public String getPageRegistrazione() {
		
		return "AreaProfiloRegistrazione";
	}
	
	
	
	
}
