package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Anagrafica;
import it.corso.model.Evento;
import it.corso.model.Libro;
import it.corso.service.EventoService;
import it.corso.service.LibroService;
import it.corso.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/homepage")
public class HomePageController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private LibroService libroService;
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@GetMapping
	public String getPage(Model model) {
		List<Evento> eventi = eventoService.getEventi();
		List<Libro> libri = libroService.getLibri();
		model.addAttribute("eventi", eventi);
		model.addAttribute("libri", libri);
	
		return "HomePage";
	}
	
	@PostMapping("/prenotalibro")
	public String prenotaLibro( HttpSession session,
			@RequestParam int idLibro,
			Model model) {
		if (session.getAttribute("utente") != null) {
			Integer idAnagrafica = ((Anagrafica) session.getAttribute("utente")).getId();
			
			String tipoPrenotazione = "libro";
			String ticket = prenotazioneService.generaTicket(idLibro, tipoPrenotazione);
			prenotazioneService.registraPrenotazione(ticket, idAnagrafica, idLibro, tipoPrenotazione);
			return "redirect:/areautente";
		} 
		return "redirect:/utente/form";
	}
	
	
}
