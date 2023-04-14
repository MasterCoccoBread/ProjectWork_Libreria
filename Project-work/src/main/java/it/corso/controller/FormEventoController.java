package it.corso.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Autore;
import it.corso.model.Evento;
import it.corso.service.AutoreService;
import it.corso.service.EventoService;
import it.corso.service.LibroService;

@Controller
@RequestMapping ("/formeventi")
public class FormEventoController {

	@Autowired
	private EventoService eventoService; 
	
	//non basta solo eventoService? perchè anche Autore? 
	
	@Autowired
	private AutoreService autoreService; 
	
	// voglio far visualizzare anche data e orario, ho bisogno di creare classi service 
	// anche per LOCALDATA e LOCALTIME? tipidato
	
	private Evento evento; 	
	
	
	@GetMapping
	public String getPage(
			Model model,
			@RequestParam(name="id", required = false) Integer id)
	{
		
		evento = (id== null) ? new Evento() : eventoService.getEventoById(id);
		
		List<Evento> eventi = eventoService.getEventi();
		List<Autore> autori = autoreService.getAutori();
		model.addAttribute("eventi", eventi);
		model.addAttribute("evento", evento);
		model.addAttribute("autori", autori);
		return "formeventi"; //serve rif. html 
	}
	
	
	@PostMapping("/salvaevento")
	public String registraEvento(
			
			@RequestParam ("descrizione") String descrizione, 
			@RequestParam ("autore") Autore autore, 
			@RequestParam ("data") LocalDate data, 
			@RequestParam ("orario") LocalTime orario)
	{	 
		
		eventoService.registraEvento(descrizione, data, orario, autore);	
		
	
	 return "redirect:/formeventi";
	 
	}
	
	
	@GetMapping("/eventi/cancella")
	public String cancellaEvento(@RequestParam("id") int id) {
		Evento evento = eventoService.getEventoById(id);
		eventoService.cancellaEvento(evento);
		return "redirect:/eventi";
	}
	
	
	
	

	

	

	

	@GetMapping("/eventi/modifica")
	public String mostraFormModificaEvento(@PathVariable("id") int id, Model model) {
		Evento evento = eventoService.getEventoById(id);
		model.addAttribute("evento", evento);
		return "form-modifica-evento";
	}

	
	
}

/*
 * Le annotazioni @PathVariable, @RequestParam e @ModelAttribute sono utilizzate in Spring per accedere ai parametri delle richieste HTTP, ma hanno scopi diversi:

@PathVariable: viene utilizzata per accedere a un valore di un parametro di un'URI. Ad esempio, se abbiamo un'URI del tipo /eventi/{id} dove id è il parametro variabile, possiamo utilizzare @PathVariable per accedere al valore di id. L'annotazione può essere utilizzata per mappare il parametro ad un tipo specifico, ad esempio int, String, UUID, ecc.

@RequestParam: viene utilizzata per accedere ai parametri di una richiesta HTTP. I parametri possono essere passati sia tramite la query string dell'URL che tramite il body della richiesta (per richieste POST). Ad esempio, se abbiamo un'URI del tipo /eventi?id=123, possiamo utilizzare @RequestParam per accedere al valore di id. L'annotazione può essere utilizzata per mappare il parametro ad un tipo specifico, ad esempio int, String, UUID, ecc.

@ModelAttribute: viene utilizzata per mappare i parametri della richiesta HTTP ad un oggetto del modello (model object). Ad esempio, se abbiamo un form che contiene diversi campi, possiamo utilizzare @ModelAttribute per mappare i valori dei campi ad un oggetto del modello. L'annotazione può anche essere utilizzata per inizializzare l'oggetto del modello prima di popolarlo con i valori dei campi del form.

In sintesi, @PathVariable è utilizzata per accedere ai parametri delle URI, @RequestParam è utilizzata per accedere ai parametri delle richieste HTTP e @ModelAttribute è utilizzata per mappare i parametri delle richieste HTTP ad un oggetto del modello. */
