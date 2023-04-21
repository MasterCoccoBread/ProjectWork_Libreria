package it.corso.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.PrenotazioniDao;
import it.corso.model.Anagrafica;
import it.corso.model.Evento;
import it.corso.model.Libro;
import it.corso.model.Prenotazione;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService {

	@Autowired
	private PrenotazioniDao prenotazioniDao;
	@Autowired
	private AnagraficaService anagraficaService;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private LibroService libroService;
	
	private Evento evento;
	private Libro libro;
	@Override
	public void registraPrenotazione(String ticket, int idAnagrafica, Integer idTipo, String tipoPrenotazione) {
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setTicket(ticket);
		//prenotazioniDao.save(prenotazione);
		Anagrafica anagrafica = anagraficaService.getAnagraficaByid(idAnagrafica);
		prenotazione.setAnagrafica(anagrafica);
		if (tipoPrenotazione.equals("evento")) {
			prenotazione.getEvento().clear();
			evento = eventoService.getEventoById(idTipo);
			prenotazione.getEvento().add(evento);
		} else {
			prenotazione.getLibro().clear();
			//for(int idLibro : idLibri) {
				libro = libroService.getLibroById(idTipo);
				prenotazione.getLibro().add(libro);
			//}
		}
		prenotazioniDao.save(prenotazione);
	}

	@Override
	public Prenotazione getPrenotazioneById(int id) {
		return prenotazioniDao.findById(id).get();
	}

	@Override
	public List<Prenotazione> getPrenotazioni() {
		return (List<Prenotazione>) prenotazioniDao.findAll();
	}

	@Override
	public void cancellaPrenotazione(Prenotazione prenotazione) {
		prenotazione.getAnagrafica().getPrenotazioni().remove(prenotazione);
		prenotazione.getEvento().clear();
		prenotazione.getLibro().clear();
		prenotazioniDao.delete(prenotazione);
	}
	
	@Override
	public String generaTicket(Integer idTipo, String tipoPrenotazione) {
		int numeroPrenotazione;
		String corpoPrenotazione, corpo, corpoTipo;
		corpo = "" + idTipo;
		if (tipoPrenotazione.equals("evento")) {
			corpoTipo = "E";
			evento = eventoService.getEventoById(idTipo); 
			List<Prenotazione> prenotazioniInEvento = evento.getPrenotazioni(); // salvo le prenotazioni di quell'evento
			if(!prenotazioniInEvento.isEmpty()) // se ci sono prenotazioni 
				numeroPrenotazione = prenotazioniInEvento.size() + 1; // salva il numero di prenotazioni e aggiungi 1
			else
				numeroPrenotazione = 1; // se non ci sono prenotazioni è la prima
			corpoPrenotazione = "" + numeroPrenotazione;
		} else {
			corpoTipo = "L";
			libro = libroService.getLibroById(idTipo); 
			List<Prenotazione> prenotazioniInLibro = libro.getPrenotazioni();
			if(!prenotazioniInLibro.isEmpty())
				numeroPrenotazione = prenotazioniInLibro.size() + 1; 
			else
				numeroPrenotazione = 1; 
			corpoPrenotazione = "" + numeroPrenotazione;
		}
		
		for (int i = 0; i < 5; i++) {
			if (corpoPrenotazione.length() < 3)
				corpoPrenotazione = "0" + corpoPrenotazione;
			if (corpo.length() < 5)
				corpo = "0" + corpo;
		}
		
		String ticket = "Ticket_" +  corpoPrenotazione + "_" + corpoTipo + corpo;
		return ticket;
	}

}