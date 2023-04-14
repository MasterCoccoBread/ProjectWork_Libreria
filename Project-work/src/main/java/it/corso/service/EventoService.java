package it.corso.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import it.corso.model.Autore;
import it.corso.model.Evento;

public interface EventoService {
	
	void registraEvento(String descrizione, LocalDate data, LocalTime orario, Autore autore);
	Evento getEventoById(int id);
	List<Evento> getEventi();
	void cancellaEvento(Evento evento);
}
