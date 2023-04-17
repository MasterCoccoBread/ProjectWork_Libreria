package it.corso.service;

import java.util.List;

import it.corso.model.Profilo;

public interface ProfiloService {
	
	boolean registraProfilo(Profilo profilo);
	Profilo getProfiloById(int id);
	List<Profilo> getProfili();
	void cancellaProfilo(Profilo profilo);

}
