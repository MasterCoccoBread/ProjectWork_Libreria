package it.corso.controller;
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
import it.corso.model.Genere;
import it.corso.model.Libro;
import it.corso.service.AutoreService;
import it.corso.service.DuplicateIsbnException;
import it.corso.service.GenereService;
import it.corso.service.LibroService;

@Controller
@RequestMapping("/formlibri")
public class FormLibroController {

	@Autowired
	private LibroService libroService;
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private GenereService genereService;
	
	private Libro libro;
	private Autore autore;
	private Genere genere;
	
	@GetMapping
	public String getPage(Model model, @RequestParam(name = "id", required = false) Integer id) {
		
		libro = (id == null) ? new Libro() : libroService.getLibroById(id);
		
		List<Libro> libri = libroService.getLibri();
		List<Autore> autori = autoreService.getAutori();
		List<Genere> generi = genereService.getGeneri();
		model.addAttribute("genere", genere);
		model.addAttribute("generi", generi);
		model.addAttribute("autore", autore);
		model.addAttribute("autori", autori);
		model.addAttribute("libro", libro);
		model.addAttribute("libri", libri);
		return "formLibro";
	}

	@PostMapping("/salvalibro")
	public String registraLibro (
			@RequestParam("titolo") String titolo,
			@RequestParam("descrizione") String descrizione,
			@RequestParam("prezzo") double prezzo,
			@RequestParam("isbn") String isbn,
			@RequestParam("genere") Genere genere,
			@RequestParam(name = "autore",required = false) Autore autore,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "immagine", required = false) MultipartFile immagine
			) throws DuplicateIsbnException {
			if(!nome.equals("")) {
				autore = new Autore();
				autoreService.registraAutore(autore, nome);
			}
		
		
		libroService.registraLibro(libro, titolo, descrizione, prezzo, isbn, genere, autore, immagine);
		return "redirect:/modificalibro";
	}
	
	@GetMapping("/cancellalibro")
	public String calcellaLibro(@RequestParam("id") int id) {
		Libro libro = libroService.getLibroById(id);
		libroService.cancellaLibro(libro);
		return "redirect:/modificalibro";
	}
}