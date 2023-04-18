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
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formutente")
public class FormAnagraficaController {
	@Autowired
	private AnagraficaService anagraficaService;

	@Autowired
	private ProfiloService profiloService;
	private Anagrafica anagraficaP;

	private Profilo profilo;
	@GetMapping
	public String getPage(Model model,@RequestParam(name="id", required = false) Integer id,
						  @RequestParam (name = "idP", required = false) Integer idP,
						  @RequestParam (name="fe", required = false) String formError)
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


	@PostMapping
	public String registraAnagrafica( @Valid
			@ModelAttribute ("anagrafica") Anagrafica anagrafica,
			BindingResult bindingResult)
	{ 
		//se ci sono errori ripresento la pagina html dove mostro i singoli messaggi
		if (bindingResult.hasErrors()){
			return "Form";

		}
		if (!anagraficaService.registraAnagrafica(anagrafica)){
			anagraficaP = anagrafica;
			return "redirect:/formutente?fe";
		}


		return "redirect:/areautente";
	}

	
	}
	
	
		
	
	
	
	
	
	
	
	
	
	
	


