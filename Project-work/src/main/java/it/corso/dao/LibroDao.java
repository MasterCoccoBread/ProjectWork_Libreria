package it.corso.dao;
import org.springframework.data.repository.CrudRepository;
import it.corso.model.Libro;

public interface LibroDao extends CrudRepository<Libro, Integer>{

}
