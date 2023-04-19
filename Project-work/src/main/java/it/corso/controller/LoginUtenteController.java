package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.service.AnagraficaService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginutente")
public class LoginUtenteController {
	
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	@GetMapping
	public String getPage(
			@RequestParam (name="fe", required = false) String logError, Model model, HttpSession session) {
		
		if(session.getAttribute("utente") != null)
		return "redirect:/areautente";
		
		model.addAttribute("logError",logError != null);
		
			return "Form";
	}

}
