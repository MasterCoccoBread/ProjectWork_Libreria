package it.corso.service;

import java.util.List;

import it.corso.model.Evento;

public interface EventoService {
	
	void registraEvento(Evento evento);
	Evento getEventoById(int id);
	List<Evento> getEventi();
	void cancellaEvento(Evento evento);
}
