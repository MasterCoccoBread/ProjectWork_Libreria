package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Libro;
import it.corso.service.LibroService;
import it.corso.service.PrenotazioneService;

@Controller
@RequestMapping("/libri")
public class LibriController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@GetMapping
	private String getPage(Model model) {
		List<Libro> libri = libroService.getLibri();
		model.addAttribute("libri", libri);
		return "CatalogoLibri";
	}
	
	@PostMapping("/prenotalibro")
	public String prenotaLibro(
			@RequestParam int idLibro) {
		Integer idAnagrafica = 1;
		
		String tipoPrenotazione = "libro";
		String ticket = prenotazioneService.generaTicket(idLibro, tipoPrenotazione);
		prenotazioneService.registraPrenotazione(ticket, idAnagrafica, idLibro, tipoPrenotazione);
		return "redirect:/libri";
	}
}
