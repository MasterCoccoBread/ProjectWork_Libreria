package it.corso.dao;
import org.springframework.data.repository.CrudRepository;
import it.corso.model.Evento;

public interface EventoDao extends CrudRepository<Evento, Integer> {

	
	// manca metodo che rende univoco un evento 
}
