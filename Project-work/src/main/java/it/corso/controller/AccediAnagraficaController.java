package it.corso.controller;
import it.corso.model.Profilo;
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
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/utente")
public class AccediAnagraficaController {
	@Autowired
	private AnagraficaService anagraficaService;

	@Autowired
	private ProfiloService profiloService;
	private Anagrafica anagraficaP;
	private Profilo profilo;
	
	
	@GetMapping ("/form")
	public String getPage(Model model,@RequestParam(name="id", required = false) Integer id,
						  @RequestParam (name = "idP", required = false) Integer idP,
						  @RequestParam (name="fe", required = false) String formError
						 )
	{				
		if(formError == null) {
			anagraficaP = (id == null) ? new Anagrafica() : anagraficaService.getAnagraficaByid(id);
			profilo = (idP == null) ? new Profilo() : profiloService.getProfiloById(idP);
		}

		model.addAttribute("anagrafica", anagraficaP);
		model.addAttribute("formError", formError!= null);
		model.addAttribute("profilo", anagraficaP.getProfilo());
		return "Form" ;
	}

 
	@PostMapping ("/form")
	public String registraAnagrafica( @Valid
			@ModelAttribute ("anagrafica") Anagrafica anagrafica,
			BindingResult bindingResult)
	{ 
		//se ci sono errori ripresento la pagina html dove mostro i singoli messaggi
		if (bindingResult.hasErrors()){
			return "redirect:/utente/form?fe";

		}
		if (!anagraficaService.registraAnagrafica(anagrafica)){
			anagraficaP = anagrafica;
			return "redirect:/utente/form?fe";
		}


		return "redirect:/registrati";
	}

	@GetMapping ("/login")
	public String getPageLogin(
			@RequestParam (name="le", required = false) String logError, Model model, HttpSession session) {
		
		if(session.getAttribute("utente") != null)
		return "redirect:/areautente";
		
		model.addAttribute("logError",logError != null);
		
			return "LoginUtente";
	}
	
	@PostMapping ("/login")
	public String gestioneLogin(
			@RequestParam ("username") String username,
			@RequestParam ("password") String password,
			HttpSession session) {
		
		if(!anagraficaService.controlloLogin(username, password, session))
			return "redirect:/utente/login?le";
		
		
		return "redirect:/areautente";
	}
	
	
	}
	
	
		
	
	
	
	
	
	
	
	
	
	
	


