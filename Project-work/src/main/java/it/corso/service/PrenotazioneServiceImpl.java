package it.corso.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import it.corso.dao.PrenotazioniDao;
import it.corso.model.Anagrafica;
import it.corso.model.Evento;
import it.corso.model.Libro;
import it.corso.model.Prenotazione;

public class PrenotazioneServiceImpl implements PrenotazioneService {

	@Autowired
	private PrenotazioniDao prenotazioniDao;
	@Autowired
	private AnagraficaService anagraficaService;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private LibroService libroService;
	
	@Override
	public void registraPrenotazione(Prenotazione prenotazione, String ticket, int idAnagrafica, int idEvento, int[] idLibri) {
		prenotazione.setTicket(ticket);
		//prenotazioniDao.save(prenotazione);
		Anagrafica anagrafica = anagraficaService.getAnagraficaByid(idAnagrafica);
		prenotazione.setAnagrafica(anagrafica);
		if ((idEvento >= 0)) {
			prenotazione.getEvento().clear();
			Evento evento = eventoService.getEventoById(idEvento);
			prenotazione.getEvento().add(evento);
		} else {
			prenotazione.getLibro().clear();
			for(int idLibro : idLibri) {
				Libro libro = libroService.getLibroById(idLibro);
				prenotazione.getLibro().add(libro);
			}
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
	public void cancellaPrenotazioneEvento(Prenotazione prenotazione) {
		prenotazione.getAnagrafica().getPrenotazioni().remove(prenotazione);
		prenotazione.getEvento().clear();
		prenotazioniDao.delete(prenotazione);
	}

	@Override
	public void cancellaPrenotazioneLibro(Prenotazione prenotazione) {
		prenotazione.getAnagrafica().getPrenotazioni().remove(prenotazione);
		prenotazione.getLibro().clear();
		prenotazioniDao.delete(prenotazione);
	}
}
