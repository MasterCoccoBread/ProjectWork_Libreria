package it.corso.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.GenereDao;
import it.corso.model.Genere;

@Service
public class GenereServiceImpl implements GenereService {

	@Autowired
	private GenereDao genereDao;
	
	@Override
	public void registraGenere(String descrizione) {
		// TODO Auto-generated method stub
	}

	@Override
	public Genere getGenereById(int id) {
		return genereDao.findById(id).get();
	}

	@Override
	public List<Genere> getGeneri() {
		return (List<Genere>) genereDao.findAll();
	}

	@Override
	public void cancellaGenere(Genere genere) {
		genereDao.delete(genere);
	}

}
