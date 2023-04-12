package it.corso.model;

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
@Table(name = "libri")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_genere", referencedColumnName = "id")
	private Genere genere;
	
	@Column(name = "prezzo")
	private double prezzo;
	
	@Column(name = "immagine")
	private String immagine;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_autore", referencedColumnName = "id")
	private Autore autore;
	
	@Column(name = "isbn")
	private String isbn;

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
	
	
	
}
