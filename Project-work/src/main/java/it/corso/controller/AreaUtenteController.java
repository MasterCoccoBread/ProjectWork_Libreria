package it.corso.controller;
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
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/areautente")
public class AreaUtenteController {
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	private Anagrafica anagrafica;
	@GetMapping 
	public String getPage(Model model, HttpSession session,
			  @RequestParam (name="fe", required = false) String formError) {
		getPagePrenotazioni(model, session);
		/*
		anagrafica = (Anagrafica) (session.getAttribute("utente"));
		List<Prenotazione> prenotazioni = anagrafica.getPrenotazioni();
		model.addAttribute("prenotazioni", prenotazioni);
		*/
		model.addAttribute("anagrafica", anagrafica);
		model.addAttribute("formError", formError!= null);
		
		//hhttp session primo controllo 
		// se session la chiave utente == null rimando al login o hp 
		// altrimenti dichiaro oggetto di tipo anagrafica = 
		//model e attributo 
		//aggiungere la vista dei dati profilo 
		//aggiungere la vista delle prenotazioni 
		return "AreaProfilo";
	}	
	
	@GetMapping("/prenotazioni")
	public String getPagePrenotazioni(Model model, HttpSession session) {
		anagrafica = (Anagrafica) (session.getAttribute("utente"));
		int idAnagrafica = anagrafica.getId();
		anagrafica = anagraficaService.getAnagraficaByid(idAnagrafica);
		model.addAttribute("prenotazioni", anagrafica.getPrenotazioni());
		return "AreaProfilo";
	}	
	
	@PostMapping ("/form")
	public String registraAnagrafica( @Valid
			@ModelAttribute ("anagrafica") Anagrafica anagrafica,
			BindingResult bindingResult) { 
		//se ci sono errori ripresento la pagina html dove mostro i singoli messaggi
		if (bindingResult.hasErrors()){
			return "redirect:/areautente?fe";
		}
		if (!anagraficaService.registraAnagrafica(anagrafica)){
			this.anagrafica = anagrafica;
			return "redirect:/areautente?fe";
		}
		return "redirect:/areautente";
	}
	
	@PostMapping("/logout")
	public String logoutUtente(HttpSession session) {
		session.invalidate();
		return "redirect:/homepage";
	}
}
