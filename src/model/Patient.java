package model;

import java.io.Serializable;

public class Patient implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -1916016033528765669L;

	private Integer id;
	private String nom;
	private String prenom;

	public Patient(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public Patient(Integer id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	@Override
	public String toString() {
		return "Patient [nom=" + nom + ", prenom=" + prenom + "]";
	}


}
