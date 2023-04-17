package it.corso.service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Autore;
import it.corso.model.Evento;
import it.corso.dao.EventoDao;


@Service
public class EventoServiceImpl implements EventoService{
	
	@Autowired
	private EventoDao eventoDao; 

	@Override
	public void registraEvento(Evento evento, String titolo, String descrizione, LocalDate data, LocalTime orario, Autore autore, MultipartFile immagine) {
	
		evento.setTitolo(titolo);
		evento.setDescrizione(descrizione);
		evento.setData(data);
		evento.setOrario(orario);
		evento.setAutore(autore);
		if (immagine != null && !immagine.isEmpty()) {
			String tipo = immagine.getContentType(); 
			try {
				evento.setImmagine("data:" + tipo + ";base64," + Base64.getEncoder().encodeToString(immagine.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
		evento.getAutore().getEventi().remove(evento);
		eventoDao.delete(evento);
	}



	

}
