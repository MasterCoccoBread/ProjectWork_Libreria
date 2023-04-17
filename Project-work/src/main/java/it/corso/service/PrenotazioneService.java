package it.corso.service;
import java.util.List;
import it.corso.model.Prenotazione;

public interface PrenotazioneService {

	void registraPrenotazione(String ticket, int idAnagrafica, Integer idEvento, Integer idLibri);
	Prenotazione getPrenotazioneById(int id);
	List<Prenotazione> getPrenotazioni();
	void cancellaPrenotazioneEvento(Prenotazione prenotazione);
	void cancellaPrenotazioneLibro(Prenotazione prenotazione);
	String generaTicket(Integer idEvento, Integer idLibri);
}
