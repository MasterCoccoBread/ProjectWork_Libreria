package it.corso.service;
import java.util.List;
import it.corso.model.Prenotazione;

public interface PrenotazioneService {

	void registraPrenotazione(String ticket, int idAnagrafica, Integer idTipo, String tipoPrenotazione);
	Prenotazione getPrenotazioneById(int id);
	List<Prenotazione> getPrenotazioni();
	public void cancellaPrenotazione(Prenotazione prenotazione);
	String generaTicket(Integer idTipo, String tipoPrenotazione);
}
