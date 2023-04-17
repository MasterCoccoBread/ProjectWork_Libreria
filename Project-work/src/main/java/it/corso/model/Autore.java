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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autori")
public class Autore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// due list, una libri una enenti entrambe onetomany
	@Column(name = "nome")
	private String nome;

	@OneToMany(
			mappedBy = "autore",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			orphanRemoval = true
	)
	private List<Evento> eventi = new ArrayList<>(); 
	
	@OneToMany(
			mappedBy = "autore",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			orphanRemoval = true
	)
	private List<Libro> libri = new ArrayList<>(); 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Evento> getEventi() {
		return eventi;
	}

	public void setEventi(List<Evento> eventi) {
		this.eventi = eventi;
	}

	public List<Libro> getLibri() {
		return libri;
	}

	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
}
