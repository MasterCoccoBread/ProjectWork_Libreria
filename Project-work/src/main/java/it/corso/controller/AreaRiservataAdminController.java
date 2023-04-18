package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Amministratore;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/areariservataadmin")
public class AreaRiservataAdminController {

	
	@GetMapping
	public String getPage(HttpSession session, Model model) {
		if(session.getAttribute("admin") == null)
			return "redirect:/loginadmin"; 
		
		Amministratore admin = (Amministratore) session.getAttribute("admin"); 
		model.addAttribute("admindue",admin);
		return "AreaRiservataAdmin";
	}
}
