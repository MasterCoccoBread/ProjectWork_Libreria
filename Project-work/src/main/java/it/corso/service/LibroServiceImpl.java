package it.corso.service;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.corso.model.Autore;
import it.corso.model.Genere;
import it.corso.model.Libro;
import it.corso.dao.LibroDao;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroDao libroDao;
	@Override
	public boolean registraLibro(Libro libro,String titolo, String descrizione, double prezzo, double sconto, String isbn, Genere genere, Autore autore, MultipartFile immagine) {

		libro.setTitolo(titolo);
		libro.setDescrizione(descrizione);
		libro.setPrezzo(prezzo);
		libro.setSconto(sconto);
		libro.setGenere(genere);
		libro.setAutore(autore);
		if (immagine != null && !immagine.isEmpty()) {
			String tipo = immagine.getContentType(); 
			try {
				libro.setImmagine("data:" + tipo + ";base64," + Base64.getEncoder().encodeToString(immagine.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Optional<Libro> existingBook = libroDao.findByIsbn(isbn);
        
		if (libro.getId() == 0 && existingBook.isPresent()) {
            return false;
        } else { 
        	libro.setIsbn(isbn);
        }
		libroDao.save(libro);
		return true;
	}

	@Override
	public Libro getLibroById(int id) {
		return libroDao.findById(id).get();
	}

	@Override
	public List<Libro> getLibri() {
		return (List<Libro>) libroDao.findAll();
	}

	@Override
	public void cancellaLibro(Libro libro) {
		libro.getGenere().getLibri().remove(libro);
		libro.getAutore().getLibri().remove(libro);
		libroDao.delete(libro);
	}
}
