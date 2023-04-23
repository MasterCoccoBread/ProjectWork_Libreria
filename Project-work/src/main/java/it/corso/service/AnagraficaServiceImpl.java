package it.corso.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.AnagraficaDao;
import it.corso.model.Anagrafica;
import it.corso.model.Profilo;
import jakarta.servlet.http.HttpSession;

@Service
public class AnagraficaServiceImpl implements AnagraficaService{

	@Autowired
	private AnagraficaDao anagraficaDao;

	private Anagrafica anagraficaEsistente;
	
	@Override
	public boolean registraAnagrafica(Anagrafica anagrafica) {

		 anagraficaEsistente = anagraficaDao.findByProfiloUsername(anagrafica.getProfilo().getUsername());
		if (anagrafica.getId()==0&&anagraficaEsistente != null) {
			return false;
		}
		anagraficaDao.save(anagrafica);
			return  true;
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
	public void cancellaAnagrafica(Anagrafica anagrafica) {

		// Bisognerebbe cancellailre il profilo perchè la FK al profilo sta nell'anagrafica. Se invece si mettesse ls FK all'anagraica nel profilo,
		// si potrebbe cancellare direttamente l'analgrafica che quindi cancellerebbe anche il profilo.

		anagraficaDao.delete(anagrafica);
	}

	@Override
	public boolean controlloLogin(String username, String password, HttpSession session) {
		
		Anagrafica anagrafica = anagraficaDao.findByProfiloUsernameAndProfiloPassword(username, password);
		
		if(anagrafica!=null) {
			session.setAttribute("utente", anagrafica);
			
			return true;
		}
		return false;
	}
}