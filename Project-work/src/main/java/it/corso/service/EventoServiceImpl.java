package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.EventoDao;
import it.corso.model.Evento;

@Service
public class EventoServiceImpl implements EventoService{
	
	@Autowired
	private EventoDao eventoDao; 
	

	@Override
	public void registraEvento(Evento evento) {
	//E' qui che serve il controllo che verifica che l'evento
	// non sia uguale ad un altro già inseirto in precedenza?
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
	public void cancellaEventoById(int id) {

		eventoDao.deleteById(id);
	}

	

}
