package it.corso.service;

import java.util.List;

import it.corso.dao.ProfiloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.model.Profilo;

@Service
public class ProfiloServiceImpl implements ProfiloService {
	
	@Autowired
	private ProfiloDao profiloDao;



	@Override
	public boolean registraProfilo(Profilo profilo) {

		//METODO MAI UTILIZZATO FIN'ORA

		Profilo profiloEsistente = profiloDao.findById(profilo.getId()).get();
		if (profilo.getId()==0) {
			return false;
		}

		profiloDao.save(profilo);
		return true;

	}

	@Override
	public Profilo getProfiloById(int id) {
		return profiloDao.findById(id).get();
	}

	@Override
	public List<Profilo> getProfili() {
		return (List<Profilo>) profiloDao.findAll();
	}

	@Override
	public void cancellaProfilo(Profilo profilo) {
		
		// remove da anagrafica? 
		System.out.println("Cancello profilo con id " + profilo.getId());
		profiloDao.deleteById( profilo.getId() );
		System.out.println("Cancellato");
	}

}
