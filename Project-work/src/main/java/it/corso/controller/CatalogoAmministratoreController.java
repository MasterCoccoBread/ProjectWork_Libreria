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
import it.corso.model.Prenotazione;
import it.corso.service.AnagraficaService;
import it.corso.service.EventoService;
import it.corso.service.LibroService;
import it.corso.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoAmministratoreController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private LibroService libroService;
	@Autowired
	private AnagraficaService anagraficaService;
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@GetMapping
	public String getPage(Model model, HttpSession session) {
		if(session.getAttribute("admin") == null)
			return "redirect:/loginadmin"; 
		model.addAttribute("adminArea", true);
		return "catalogoAmministratore";
	}
	
	@GetMapping("/libri")
	public String getPageLibro(Model model) {
		List<Libro> libri = libroService.getLibri();
		model.addAttribute("libri", libri);
		model.addAttribute("catalogo", "libri");
		model.addAttribute("adminArea", false);
		return "catalogoAmministratore";
	}
	
	@GetMapping("/eventi")
	public String getPageEvento(Model model) {
		List<Evento> eventi = eventoService.getEventi();
		model.addAttribute("eventi", eventi);
		model.addAttribute("catalogo", "eventi");
		model.addAttribute("adminArea", false);
		return "catalogoAmministratore";
	}
	
	@GetMapping("/utenti")
	public String getPageUtente(Model model) {
		List<Anagrafica> anagrafiche = anagraficaService.getAnagrafiche();
		model.addAttribute("anagrafiche",anagrafiche);
		model.addAttribute("catalogo", "utenti");
		model.addAttribute("adminArea", false);
		return "catalogoAmministratore";
	}
	
	@GetMapping("/prenotazioni")
	public String getPagePrenotazione(Model model) {
		List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioni();
		model.addAttribute("prenotazioni", prenotazioni);
		model.addAttribute("catalogo", "prenotazioni");
		model.addAttribute("adminArea", false);
		return "catalogoAmministratore";
	}
	
	@GetMapping("/cancellaanagrafica")
	public String cancellaAnagrafica(@RequestParam("id") int id) {
		Anagrafica anagrafica = anagraficaService.getAnagraficaByid(id);
		anagraficaService.cancellaAnagrafica(anagrafica);
		return "redirect:/catalogo/utenti";
	}
	
	@GetMapping("/cancellaprenotazione")
	public String cancellaPrenotazione(@RequestParam("id") int id) {
		Prenotazione prenotazione = prenotazioneService.getPrenotazioneById(id);
		prenotazioneService.cancellaPrenotazione(prenotazione);
		return "redirect:/catalogo/prenotazioni";
	}
	
	@PostMapping("/logout")
	public String logoutAmministratore(HttpSession session) {
		session.invalidate();
		return "redirect:/homepage";
	}
}
