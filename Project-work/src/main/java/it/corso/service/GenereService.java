package it.corso.service;

import java.util.List;

import it.corso.model.Genere;

public interface GenereService {

	void registraGenere(String descrizione);
	Genere getGenereById(int id);
	List<Genere> getGeneri();
	void cancellaGenere(Genere genere);
}
