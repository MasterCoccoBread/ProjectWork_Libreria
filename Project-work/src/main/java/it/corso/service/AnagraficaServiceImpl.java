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
	private ProfiloService profiloService;
	@Autowired
	private AnagraficaDao anagraficaDao;

/*	@Override
	public Anagrafica createAnagrafica(Anagrafica anagrafica) throws DuplicateEntryException {
		// Verifica se l'anagrafica esiste già
		if (anagraficaDao.findByTelefonoAndProfilo(anagrafica.getTelefono(), anagrafica.getProfilo()).isPresent() ||
				anagraficaRepository.findByEmailAndProfilo(anagrafica.getEmail(), anagrafica.getProfilo()).isPresent() ||
				anagraficaRepository.findByUsernameAndProfilo(anagrafica.getUsername(), anagrafica.getProfilo()).isPresent()) {
			throw new DuplicateEntryException("L'anagrafica esiste già per questo profilo");
		}
		return anagraficaRepository.save(anagrafica);
	} */
	@Override
	public void registraAnagrafica(Anagrafica anagrafica) {

		Anagrafica anagraficaEsistente = anagraficaDao.findByTelefono(anagrafica.getTelefono());
		if (anagraficaEsistente != null) {
			throw new DuplicateEntryException("Il numero di telefono inserito esiste già.");
		}
		anagraficaDao.save(anagrafica);

		//save fa già da solo il controllo ID, se valido o null (solo integer può essere null, no int)

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
	public void cancellaAnagrafica(int id) {

		// Bisogna cancellailre il profilo perchè la FK al profilo sta nell'anagrafica. Se invece si mettesse ls FK all'anagraica nel profilo,
		// si potrebbe cancellare direttamente l'analgrafica che quindi cancellerebbe anche il profilo.


		//relativo al cascade All o Refresh
		// cancellazione profilo 
		// cancellazione della prenotazione?
		Anagrafica anagrafica = getAnagraficaByid(id);
		anagraficaDao.delete(anagrafica);
		profiloService.cancellaProfilo(anagrafica.getProfilo());
		//cancello prima il profilo e poi l'anagrafica (forzando) perchè non

	}

}
