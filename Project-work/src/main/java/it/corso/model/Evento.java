package it.corso.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento {

	private int id;
	
	private String descrizione;

	private LocalDate data;
	
	private LocalTime tempo; 
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getTempo() {
		return tempo;
	}

	public void setTempo(LocalTime tempo) {
		this.tempo = tempo;
	}

	
}
