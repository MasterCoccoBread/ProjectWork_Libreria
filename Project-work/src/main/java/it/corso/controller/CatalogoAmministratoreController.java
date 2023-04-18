package it.corso.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.corso.model.Evento;
import it.corso.model.Libro;
import it.corso.service.EventoService;
import it.corso.service.LibroService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalogo")
public class CatalogoAmministratoreController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private LibroService libroService;
	
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
		model.addAttribute("catalogo", false);
		model.addAttribute("adminArea", false);
		return "catalogoAmministratore";
	}
	
	@GetMapping("/eventi")
	public String getPageEvento(Model model) {
		List<Evento> eventi = eventoService.getEventi();
		model.addAttribute("eventi", eventi);
		model.addAttribute("catalogo", true);
		model.addAttribute("adminArea", false);
		return "catalogoAmministratore";
	}
}
