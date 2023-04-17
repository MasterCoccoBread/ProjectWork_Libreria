package it.corso.dao;

import org.springframework.data.repository.CrudRepository;
import it.corso.model.Profilo;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfiloDao  extends CrudRepository<Profilo, Integer>{

    Profilo findByEmail(String email);
    Profilo findByUsername(String username);


}
