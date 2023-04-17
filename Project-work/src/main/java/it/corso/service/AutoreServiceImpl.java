package it.corso.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.AutoreDao;
import it.corso.model.Autore;

@Service
public class AutoreServiceImpl implements AutoreService {

	@Autowired
	private AutoreDao autoreDao;
	
	@Override
	public void registraAutore(Autore autore, String nome) {
		autore.setNome(nome);
		autoreDao.save(autore);
	}

	@Override
	public Autore getAutoreById(int id) {
		return autoreDao.findById(id).get();
	}

	@Override
	public List<Autore> getAutori() {
		return (List<Autore>) autoreDao.findAll();
	}

	@Override
	public void cancellaAutore(Autore autore) {
		autoreDao.delete(autore);
	}

}
