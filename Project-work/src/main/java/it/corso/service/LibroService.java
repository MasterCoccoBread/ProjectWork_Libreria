package it.corso.service;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Autore;
import it.corso.model.Genere;
import it.corso.model.Libro;

public interface LibroService {

	void registraLibro(Libro libro, String titolo, String descrizione, double prezzo, String isbn, Genere genere, Autore autore, MultipartFile immagine)throws DuplicateIsbnException;
	Libro getLibroById(int id);
	List<Libro> getLibri();
	void cancellaLibro(Libro libro);
}
