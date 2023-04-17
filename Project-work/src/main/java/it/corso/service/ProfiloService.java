package it.corso.service;

import java.util.List;

import it.corso.model.Profilo;

public interface ProfiloService {
	
	void registraProfilo(Profilo profilo,String username, String password, String email);
	Profilo getProfiloById(int id);
	List<Profilo> getProfili();
	void cancellaProfilo(Profilo profilo);

}
