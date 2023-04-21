package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/areautente")
public class AreaUtenteController {
	
	@GetMapping 
	public String getPage(Model model, HttpSession session) {
		
		//hhttp session primo controllo 
		// se session la chiave utente == null rimando al login o hp 
		// altrimenti dichiaro oggetto di tipo anagrafica = 
		//model e attributo 
		//aggiungere la vista dei dati profilo 
		//aggiungere la vista delle prenotazioni 
		return "AreaProfilo";
	}	
	
}
