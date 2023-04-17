package it.corso.dao;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import it.corso.model.Libro;

public interface LibroDao extends CrudRepository<Libro, Integer>{
	Optional<Libro> findByIsbn(String isbn);
}
