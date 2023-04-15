package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.AnagraficaDao;
import it.corso.model.Anagrafica;
import it.corso.model.Profilo;

@Service
public class AnagraficaServiceImpl implements AnagraficaService{
	
	@Autowired
	private AnagraficaDao anagraficaDao; 

	@Override
	public void registraAnagrafica(Anagrafica anagrafica, String nome, String cognome, String telefono,
			Profilo profilo) {
		
		anagrafica.setNome(nome);
		anagrafica.setCognome(cognome);
		anagrafica.setTelefono(telefono);
		anagrafica.setProfilo(profilo);
		
		
		
		anagraficaDao.save(anagrafica);
		
		
		
	}

	@Override
	public Anagrafica getAnagraficaByid(int id) {
		return anagraficaDao.findById(id).get();
	}

	@Override
	public List<Anagrafica> getAnagrafiche() {
		return (List<Anagrafica>) anagraficaDao.findAll();
	}

	@Override
	public void cancellaAnagrafica(Anagrafica anagrafica) {
		
		//relativo al cascade All o Refresh 
		// cancellazione profilo 
		// cancellazione della prenotazione? 
		
		anagraficaDao.delete(anagrafica);
		
	}

}
