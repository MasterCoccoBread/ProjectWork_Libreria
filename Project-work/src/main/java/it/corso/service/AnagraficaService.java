package it.corso.service;

import java.util.List;

import it.corso.model.Anagrafica;
import it.corso.model.Profilo;

public interface AnagraficaService {
	
	void registraAnagrafica(
		Integer id, String nome, String cognome,
			String telefono, Profilo profilo);
	Anagrafica getAnagraficaByid(int id);
	List<Anagrafica> getAnagrafiche();
	void cancellaAnagrafica(Anagrafica anagrafica);
	

}
