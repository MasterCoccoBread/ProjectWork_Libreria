package it.corso.model;

public class Libro {

	private int id;
	
	private String titolo;
	
	private String descrizione;
	
	//private Genere genere;
	
	private double prezzo;
	
	private String immagine;

	//private Autore autore;
	
	private String codiceIsbn;

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

	public String getCodiceIsbn() {
		return codiceIsbn;
	}

	public void setCodiceIsbn(String codiceIsbn) {
		this.codiceIsbn = codiceIsbn;
	}
	
	
	
}
