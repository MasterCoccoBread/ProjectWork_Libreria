package it.corso.service;
import java.util.List;
import it.corso.model.Prenotazione;

public interface PrenotazioneService {

	void registraPrenotazione(Prenotazione prenotazione, String ticket, int idAnagrafica, int idEvento, int[] idLibri);
	Prenotazione getPrenotazioneById(int id);
	List<Prenotazione> getPrenotazioni();
	void cancellaPrenotazioneEvento(Prenotazione prenotazione);
	void cancellaPrenotazioneLibro(Prenotazione prenotazione);
}
