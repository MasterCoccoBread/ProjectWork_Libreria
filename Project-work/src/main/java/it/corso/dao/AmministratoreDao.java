package it.corso.dao;
import org.springframework.data.repository.CrudRepository;
import it.corso.model.Amministratore;

public interface AmministratoreDao extends CrudRepository<Amministratore, Integer>{

	Amministratore findByUsernameAndPassword(String username, String password);
	
}
