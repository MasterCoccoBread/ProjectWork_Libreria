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

	//Alternativa metodo @modelAttribute @Valid lezione 7
	@Override
	public void registraEvento(Integer id, String descrizione, LocalDate data, LocalTime orario, Autore autore) {
	
		//controllo ID da solo - non si vede il controllo 
		
		Evento evento = new Evento();
		evento.setId(id);
		evento.setDescrizione(descrizione);
		evento.setData(data);
		evento.setOrario(orario);
		evento.setAutore(autore);

		eventoDao.save(evento);
		//save fa già da solo il controllo ID, se valido o null (solo integer può essere null, no int)
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
