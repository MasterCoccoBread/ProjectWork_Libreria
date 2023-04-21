package it.corso.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "prezzo")
	private double prezzo;
	
	@Column(name = "sconto")
	private double sconto;
	
	@Column(name = "immagine")
	private String immagine;
	
	@Column(name = "isbn")
	private String isbn;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_genere", referencedColumnName = "id")
	private Genere genere;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_autore", referencedColumnName = "id")
	private Autore autore;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(
			name = "prenotazione_libro",
			joinColumns = @JoinColumn(name = "id_libro", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_prenotazione", referencedColumnName = "id")
	)
	private List<Prenotazione> prenotazioni = new ArrayList<>(); 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String codiceIsbn) {
		this.isbn = codiceIsbn;
	}
	
	public Genere getGenere() {
		return genere;
	}
	
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	
	public Autore getAutore() {
		return autore;
	}
	
	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	
}
