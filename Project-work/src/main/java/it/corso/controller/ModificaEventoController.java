package it.corso.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.corso.model.Evento;
import it.corso.service.EventoService;

@Controller
@RequestMapping("/modificaevento")
public class ModificaEventoController {

	@Autowired
	private EventoService eventoService;
	
	@GetMapping
	public String getPage(Model model) {
		List<Evento> eventi = eventoService.getEventi();
		model.addAttribute("eventi", eventi);
		return "ModificaEvento";
	}
}
