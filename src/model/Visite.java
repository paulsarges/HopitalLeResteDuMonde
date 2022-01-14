package model;

import java.time.LocalDate;

public class Visite {

	private static Integer numero;
	private Patient patient;
	private Medecin medecin;
	private double prix;
	private int salle;
	private LocalDate dateVisite;
	
	
	public Visite(Patient patient, Medecin medecin, double prix, int salle, LocalDate dateVisite) {
		numero++;
		this.patient = patient;
		this.medecin = medecin;
		this.prix = prix;
		this.salle = salle;
		this.dateVisite = dateVisite;
	}


	public static Integer getNumero() {
		return numero;
	}


	public static void setNumero(Integer numero) {
		Visite.numero = numero;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Medecin getMedecin() {
		return medecin;
	}


	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public int getSalle() {
		return salle;
	}


	public void setSalle(int salle) {
		this.salle = salle;
	}


	public LocalDate getDateVisite() {
		return dateVisite;
	}


	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}


	@Override
	public String toString() {
		return "Visite [medecin=" + medecin + ", prix=" + prix + ", salle=" + salle + ", dateVisite=" + dateVisite
				+ "]";
	}


	
	
}
