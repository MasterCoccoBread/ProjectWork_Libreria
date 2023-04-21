package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrati")
public class RegistratiAnagraficaController {
	
	@GetMapping 	
	public String getPageRegistrazione() {
		
		return "AreaProfiloRegistrazione";
	}

}
