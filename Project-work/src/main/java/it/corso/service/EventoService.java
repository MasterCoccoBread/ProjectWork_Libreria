package it.corso.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Autore;
import it.corso.model.Evento;

public interface EventoService {
	
	void registraEvento(Evento evento,String titolo, String descrizione, LocalDate data, LocalTime orario, Autore autore, MultipartFile immagine);
	Evento getEventoById(int id);
	List<Evento> getEventi();
	void cancellaEvento(Evento evento);
}
