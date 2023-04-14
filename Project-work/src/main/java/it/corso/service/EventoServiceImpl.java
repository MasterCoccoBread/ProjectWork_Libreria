package it.corso.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.EventoDao;
import it.corso.model.Autore;
import it.corso.model.Evento;


@Service
public class EventoServiceImpl implements EventoService{
	
	@Autowired
	private EventoDao eventoDao; 

	@Override
	//che differenza fra aggiungere gli attributi o l'oggetto evento directly?
	public void registraEvento(String descrizione, LocalDate data, LocalTime orario, Autore autore) {
	
		Evento evento = new Evento(); 
		
		evento.setDescrizione(descrizione);
		evento.setData(data);
		evento.setOrario(orario);
		
		//E' qui che serve un controllo che verifica che l'evento
		// non sia uguale ad un altro già inseto in precedenza
			
		
		eventoDao.save(evento);
	}

	@Override
	public Evento getEventoById(int id) {
		return eventoDao.findById(id).get();
	}

	@Override
	public List<Evento> getEventi() {
		return (List<Evento>) eventoDao.findAll();
	}

	@Override
	public void cancellaEvento(Evento evento) {
		eventoDao.delete(evento);
	}



	

}
