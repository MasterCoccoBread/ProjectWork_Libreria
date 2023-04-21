package it.corso.dao;
import org.springframework.data.repository.CrudRepository;
import it.corso.model.Anagrafica;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagraficaDao extends CrudRepository<Anagrafica, Integer>{
	
	
	Anagrafica findByProfiloUsername(String username);
	Anagrafica findByProfiloUsernameAndProfiloPassword(String username, String password);

	
}
