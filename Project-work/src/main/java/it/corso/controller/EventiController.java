package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.corso.service.EventoService;
import it.corso.service.PrenotazioneService;

@Controller
@RequestMapping("/eventi")
public class EventiController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@GetMapping
	public String getPage() {
		return "eventi";
	}
	
	@PostMapping("/prenotaevento")
	public String prenotaEvento(
			@RequestParam(name = "idAnagrafica") int idAnagrafica,
			@RequestParam(name = "idEvento", required = false) Integer idEvento,
			@RequestParam(name = "idLibro", required = false) Integer idLibri) {
		
		String ticket = prenotazioneService.generaTicket(idEvento, idLibri);
		prenotazioneService.registraPrenotazione(ticket, idAnagrafica, idEvento, idLibri);
		return "redirect:/eventi";
	}
}
