package it.corso.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "eventi")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "orario")
	private LocalTime orario; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_autore", referencedColumnName = "id")
	private Autore autore;
// many to one con autore 
	// lista prenotazioni  one to many
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

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}

	
}
