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
import it.corso.model.Genere;
import it.corso.model.Libro;
import it.corso.service.GenereService;
import it.corso.service.LibroService;
import it.corso.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/libri")
public class LibriController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private GenereService genereService;
	@Autowired
	private PrenotazioneService prenotazioneService;
	private Libro libro;
	private Genere genere;
	List<Libro> libri;
	@GetMapping
	private String getPage(Model model,
			@RequestParam(name = "ok", required = false) String ok,
			@RequestParam(name = "idGenere", required = false) Integer idGenere,
			@RequestParam(name = "idLibro", required = false) Integer idLibro) {
		
		if (idGenere == null && idLibro == null) {
			libri = libroService.getLibri();
			model.addAttribute("libri", libri);
			model.addAttribute("totale", true);
		} else if (idGenere != null) {
			genere = genereService.getGenereById(idGenere);
			libri = genere.getLibri();
			model.addAttribute("libri", libri);
			model.addAttribute("totale", true);
		} else if (idLibro != null) {
			libro = libroService.getLibroById(idLibro);
			model.addAttribute("libro", libro);
			model.addAttribute("totale", false);
		}
		List<Genere> generi = genereService.getGeneri();
		
		model.addAttribute("generi", generi);
		model.addAttribute("ok", ok != null);
		return "CatalogoLibri";
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
			//model.addAttribute("pren", true);
			return "redirect:/areautente";
		} 
		return "redirect:/utente/form";
	}
}
