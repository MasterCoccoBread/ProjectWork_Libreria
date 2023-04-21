package it.corso.controller;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import it.corso.model.Autore;
import it.corso.model.Evento;
import it.corso.model.Genere;
import it.corso.model.Libro;
import it.corso.service.AutoreService;
import it.corso.service.EventoService;
import it.corso.service.GenereService;
import it.corso.service.LibroService;

@Controller
@RequestMapping("/form")
public class FormAmministratoreController {

	@Autowired
	private EventoService eventoService; 
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private GenereService genereService;
	
	private Evento evento;
	private Libro libro;
	private Autore autore;
	private Genere genere;
	
	String messaggio;
	
	@GetMapping("/libri")
	public String getPageLibri(Model model,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "fe", required = false) String formError) {
		if(formError == null) 
			libro = (id == null) ? new Libro() : libroService.getLibroById(id);
		
		List<Libro> libri = libroService.getLibri();
		List<Autore> autori = autoreService.getAutori();
		List<Genere> generi = genereService.getGeneri();
		model.addAttribute("formError", formError != null);
		model.addAttribute("genere", genere);
		model.addAttribute("generi", generi);
		model.addAttribute("autore", autore);
		model.addAttribute("autori", autori);
		model.addAttribute("libro", libro);
		model.addAttribute("libri", libri);
		model.addAttribute("divform", false);
		return "formAmministratore";
	}
	
	@GetMapping("/eventi")
	public String getPageEventi(Model model,
			@RequestParam(name="id", required = false) Integer id) {
		
		evento = (id == null) ? new Evento() : eventoService.getEventoById(id);
		
		List<Evento> eventi = eventoService.getEventi();
		List<Autore> autori = autoreService.getAutori();
		model.addAttribute("eventi", eventi);
		model.addAttribute("evento", evento);
		model.addAttribute("autori", autori);
		model.addAttribute("autore", autore);
		model.addAttribute("divform", true);
		return "formAmministratore";
	}
	
	@PostMapping("/salvalibro")
	public String registraLibro (
			@RequestParam("titolo") String titolo,
			@RequestParam("descrizione") String descrizione,
			@RequestParam("prezzo") double prezzo,
			@RequestParam("sconto") double sconto,
			@RequestParam ("isbn") String isbn,
			@RequestParam("genere") Genere genere,
			@RequestParam(name = "autore",required = false) Autore autore,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "immagine", required = false) MultipartFile immagine) {
		
		if(!nome.equals("")) {
			autore = new Autore();
			autoreService.registraAutore(autore, nome);
		}
		 
		if(!libroService.registraLibro(libro, titolo, descrizione, prezzo, sconto, isbn, genere, autore, immagine)){
			return "redirect:/form/libri?fe";
		}
		
		return "redirect:/catalogo/libri";
	}
	
	@PostMapping("/salvaevento")
	public String registraEvento(
			@RequestParam ("titolo") String titolo,
			@RequestParam ("descrizione") String descrizione,
			@RequestParam ("data") LocalDate data, 
			@RequestParam ("orario") LocalTime orario,
			@RequestParam(name = "autore",required = false) Autore autore,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "immagine", required = false) MultipartFile immagine)
	{	 
		if(!nome.equals("")) {
			autore = new Autore();
			autoreService.registraAutore(autore, nome);
		}
		
		eventoService.registraEvento(evento, titolo, descrizione, data, orario, autore, immagine);
		return "redirect:/catalogo/eventi";
	}
	
	@GetMapping("/cancellalibro")
	public String calcellaLibro(@RequestParam("id") int id) {
		Libro libro = libroService.getLibroById(id);
		libroService.cancellaLibro(libro);
		return "redirect:/catalogo/libri";
	}
	
	@GetMapping("/cancellaevento")
	public String cancellaEvento(@RequestParam("id") int id) {
		Evento evento = eventoService.getEventoById(id);
		eventoService.cancellaEvento(evento);
		return "redirect:/catalogo/eventi";
	}
}
