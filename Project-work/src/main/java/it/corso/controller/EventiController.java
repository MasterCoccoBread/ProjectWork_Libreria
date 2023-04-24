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
import it.corso.service.EventoService;
import it.corso.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/eventi")
public class EventiController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@GetMapping
	public String getPage(Model model) {
		List<Evento> eventi = eventoService.getEventi();
		model.addAttribute("eventi", eventi);
		return "Eventi";
	}
	
	@PostMapping("/prenotaevento")
	public String prenotaEvento( HttpSession session,
			@RequestParam int idEvento) {
		if (session.getAttribute("utente") != null) {
			Integer idAnagrafica = ((Anagrafica) session.getAttribute("utente")).getId();
			String tipoPrenotazione = "evento";
			String ticket = prenotazioneService.generaTicket(idEvento, tipoPrenotazione);
			prenotazioneService.registraPrenotazione(ticket, idAnagrafica, idEvento, tipoPrenotazione);
			return "redirect:/areautente";
		}
		return "redirect:/utente/form";
	}
}
