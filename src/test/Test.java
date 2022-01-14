package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import dao.DAOCompte;
import dao.DAOPatient;
import dao.DAOVisite;
import model.Compte;
import model.Medecin;
import model.Patient;
import model.Secretaire;
import model.Visite;

public class Test {
	static Compte connected = null;
	static DAOCompte daoC = new DAOCompte();
	static DAOPatient daoP = new DAOPatient();
	static DAOVisite daoV = new DAOVisite();
	static LinkedList<Patient> listedAttente = new LinkedList();
	static List<Visite> listVisite = new ArrayList();
	
	
	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}

	public static double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}

	public static int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static void menuPrincipal() {

		System.out.println("Menu principal");
		System.out.println("1 - Se connecter");
		System.out.println("2 - Fermer l'appli");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : connexion();break;
		case 2 : System.exit(0);break;
		}
		menuPrincipal();
	}
	
	public static void connexion() {

		System.out.println("Connexion");
		String login = saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");
		connected= daoC.seConnecter(login, password);

		if(connected instanceof Medecin) {menuMedecin();}
		else if(connected instanceof Secretaire) {menuSecretaire(false);}
		else if(connected ==null) 
		{
			System.out.println("Identifiants invalides !");
		}
		menuPrincipal();


	}
	
	public static void menuSecretaire(boolean pause) {
		if (!pause) {

			System.out.println("Menu Secrétaire");
			System.out.println("1 - Mettre sur la liste d'attente");
			System.out.println("2 - Consulter la liste d'attente");
			System.out.println("3 - Partir en Pause");
			System.out.println("4 - Se deconnecter");
	
			int choix = saisieInt("Choisir un menu");
	
			switch(choix) 
			{
			case 1 : addAttente();break;
			case 2 : showAttente();break;
			case 3 : menuSecretaire(true);break;
			case 4 : connected=null;menuPrincipal();break;
			}
		}
		
		else {

			System.out.println("Menu Vendeur");
			System.out.println("1 - Revenir pause");
			System.out.println("2 - Se déconnecter");
	
			int choix = saisieInt("Choisir un menu");
	
			switch(choix) 
			{
			case 1 : menuSecretaire(false);break;
			case 2 : connected=null;menuPrincipal();break;
			}
		}

		menuSecretaire(false);

	}
	
	public static void menuMedecin() {

		System.out.println("Menu Secrétaire");
		System.out.println("1 - Patient suivant");
		System.out.println("2 - Consulter la liste d'attente");
		System.out.println("3 - Sauvegarder les visites");
		System.out.println("4 - Se deconnecter");
	
		int choix = saisieInt("Choisir un menu");
	
		switch(choix) 
		{
			case 1 : updateAttente();break;
			case 2 : showAttente();break;
			case 3 : saveVisite();break;
			case 4 : connected=null;menuPrincipal();break;
		
		}
		menuMedecin();

	}
	
	public static void addAttente() {
		System.out.println("Identifiant patient :");
		int id= saisieInt("Entrer id patient");
		Patient p = daoP.findById(id);
		if (p == null) {
			addPatient();
		}
		else {
			listedAttente.add(p);
		}
		
	}
	
	public static void addPatient() {
		String nom = saisieString("Entrer nom");
		String prenom = saisieString("Entrer prenom");
		Patient p = new Patient(nom, prenom);
		daoP.insert(p);
		listedAttente.add(p);
	}
	
	public static void showAttente() {
		System.out.println("Liste d'attente");
		for (Patient p : listedAttente) {
			System.out.println(p);
		}
	}
	
	public static void updateAttente() {
		Double prix = saisieDouble("Prix de la consultation :");
		int salle = saisieInt("Entrer numero de la salle :");
		Visite v = new Visite(listedAttente.peek(), (Medecin) connected, prix, salle, LocalDate.now() );
		listVisite.add(v);
		checkListVisite();
		listedAttente.poll();
		System.out.println("Le client suivant est" + listedAttente.peek());
		
	}
	
	public static void saveVisite() {
		for (Visite v : listVisite) {
			daoV.insert(v);
		}
		System.out.println("La liste des visiteurs a bien été sauvegardée");
	}
	
	public static void checkListVisite() {
		if (listVisite.size() == 10) {
			saveVisite();			
			listVisite.removeAll(listVisite); //vider la liste des visites en local
		}
			
	}
	
	

}
