package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Evento;
import it.corso.service.EventoService;

@Controller
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@GetMapping("/eventi")
	public String mostraEventi(Model model) {
		List<Evento> eventi = eventoService.getEventi();
		model.addAttribute("eventi", eventi);
		return "lista-eventi";
	}

	@GetMapping("/eventi")
	public String mostraDettaglioEvento(@RequestParam("id") int id, Model model) {
		Evento evento = eventoService.getEventoById(id);
		model.addAttribute("evento", evento);
		return "dettaglio-evento";
	}

	@GetMapping("/eventi/nuovo")
	public String mostraFormNuovoEvento(Model model) {
		Evento evento = new Evento();
		model.addAttribute("evento", evento);
		return "form-nuovo-evento";
	}

	@PostMapping("/eventi/nuovo")
	public String salvaNuovoEvento(@ModelAttribute("evento") Evento evento) {
		eventoService.registraEvento(evento);
		return "redirect:/eventi";
	}

	@GetMapping("/eventi/{id}/modifica")
	public String mostraFormModificaEvento(@PathVariable("id") int id, Model model) {
		Evento evento = eventoService.getEventoById(id);
		model.addAttribute("evento", evento);
		return "form-modifica-evento";
	}

	@PostMapping("/eventi/{id}/modifica")
	public String salvaModificaEvento(@ModelAttribute("evento") Evento evento) {
		eventoService.registraEvento(evento);
		return "redirect:/eventi";
	}

	@GetMapping("/eventi/{id}/cancella")
	public String cancellaEvento(@PathVariable("id") int id) {
		eventoService.cancellaEventoById(id);
		return "redirect:/eventi";
	}
}

/*
 * Le annotazioni @PathVariable, @RequestParam e @ModelAttribute sono utilizzate in Spring per accedere ai parametri delle richieste HTTP, ma hanno scopi diversi:

@PathVariable: viene utilizzata per accedere a un valore di un parametro di un'URI. Ad esempio, se abbiamo un'URI del tipo /eventi/{id} dove id è il parametro variabile, possiamo utilizzare @PathVariable per accedere al valore di id. L'annotazione può essere utilizzata per mappare il parametro ad un tipo specifico, ad esempio int, String, UUID, ecc.

@RequestParam: viene utilizzata per accedere ai parametri di una richiesta HTTP. I parametri possono essere passati sia tramite la query string dell'URL che tramite il body della richiesta (per richieste POST). Ad esempio, se abbiamo un'URI del tipo /eventi?id=123, possiamo utilizzare @RequestParam per accedere al valore di id. L'annotazione può essere utilizzata per mappare il parametro ad un tipo specifico, ad esempio int, String, UUID, ecc.

@ModelAttribute: viene utilizzata per mappare i parametri della richiesta HTTP ad un oggetto del modello (model object). Ad esempio, se abbiamo un form che contiene diversi campi, possiamo utilizzare @ModelAttribute per mappare i valori dei campi ad un oggetto del modello. L'annotazione può anche essere utilizzata per inizializzare l'oggetto del modello prima di popolarlo con i valori dei campi del form.

In sintesi, @PathVariable è utilizzata per accedere ai parametri delle URI, @RequestParam è utilizzata per accedere ai parametri delle richieste HTTP e @ModelAttribute è utilizzata per mappare i parametri delle richieste HTTP ad un oggetto del modello. */
