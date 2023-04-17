package it.corso.service;
import java.util.List;
import it.corso.model.Autore;

public interface AutoreService {

	void registraAutore(Autore autore, String nome);
	Autore getAutoreById(int id);
	List<Autore> getAutori();
	void cancellaAutore(Autore autore);
}
