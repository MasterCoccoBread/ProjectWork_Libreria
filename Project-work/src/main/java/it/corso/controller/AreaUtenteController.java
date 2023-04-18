package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/areautente")
public class AreaUtenteController {
	
	@GetMapping
	public String getPage() {
		return "AreaProfilo";
	}

}
