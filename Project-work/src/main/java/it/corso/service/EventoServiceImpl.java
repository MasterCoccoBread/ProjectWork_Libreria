package it.corso.service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.model.Autore;
import it.corso.model.Evento;
import it.corso.dao.EventoDao;


@Service
public class EventoServiceImpl implements EventoService{
	
	@Autowired
	private EventoDao eventoDao; 

	//che differenza fra aggiungere gli attributi o l'oggetto evento directly?
	@Override
	public void registraEvento(Evento evento, String descrizione, LocalDate data, LocalTime orario, Autore autore) {
	
		evento.setDescrizione(descrizione);
		evento.setData(data);
		evento.setOrario(orario);
		evento.setAutore(autore);
		//E' qui che serve un controllo che verifica che l'evento
		// non sia uguale ad un altro già inseto in precedenza
		//al psoto di isbn usiamo codiceID 
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
