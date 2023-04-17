package it.corso.service;

import java.util.List;

import it.corso.model.Anagrafica;
import it.corso.model.Profilo;

public interface AnagraficaService {
	
	boolean registraAnagrafica(
		Anagrafica anagrafica);
	Anagrafica getAnagraficaByid(int id);
	List<Anagrafica> getAnagrafiche();
	void cancellaAnagrafica(int id);
	

}