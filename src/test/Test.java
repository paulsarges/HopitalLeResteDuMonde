package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	static Integer salle = null;

	public static String saisieString(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}

	public static double saisieDouble(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}

	public static int saisieInt(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();
	}

	public static void main(String[] args) {
		menuPrincipal();

	}

	public static void menuPrincipal() {

		System.out.println("Menu principal");
		System.out.println("1 - Se connecter");
		System.out.println("2 - Fermer l'appli");

		int choix = saisieInt("Choisir un menu");

		switch (choix) {
		case 1:
			connexion();
			break;
		case 2:
			System.exit(0);
			break;
		}
		menuPrincipal();
	}

	public static void connexion() {

		System.out.println("Connexion");
		String login = saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");
		connected = daoC.seConnecter(login, password);

		if (connected instanceof Medecin) {
			salle = saisieInt("Entrer salle");
			menuMedecin();
		} else if (connected instanceof Secretaire) {
			menuSecretaire(false);
		} else if (connected == null) {
			System.out.println("Identifiants invalides !");
		}
		menuPrincipal();

	}

	public static void menuSecretaire(boolean pause) {
		if (!pause) {

			System.out.println("Menu Secr�taire");
			System.out.println("1 - Mettre sur la liste d'attente");
			System.out.println("2 - Consulter la liste d'attente");
			System.out.println("3 - Afficher les visites d'un patient");
			System.out.println("4 - Partir en Pause");
			System.out.println("5 - Se deconnecter");

			int choix = saisieInt("Choisir un menu");

			switch (choix) {
			case 1:
				addAttente();
				break;
			case 2:
				showAttente();
				break;
			case 3:
				showVisite();
				break;
			case 4:
				saveAttente();
				menuSecretaire(true);
				;
				break;
			case 5:
				connected = null;
				menuPrincipal();
				break;
			}
		}

		else {

			System.out.println("Menu Secretaire");
			System.out.println("1 - Revenir pause");
			System.out.println("2 - Se d�connecter");

			int choix = saisieInt("Choisir un menu");

			switch (choix) {
			case 1:
				openAttente();
				menuSecretaire(false);
				break;
			case 2:
				connected = null;
				menuPrincipal();
				break;
			}
		}

		menuSecretaire(false);

	}

	public static void menuMedecin() {

		System.out.println("Menu Medecin");
		System.out.println("1 - Patient suivant");
		System.out.println("2 - Consulter la liste d'attente");
		System.out.println("3 - Sauvegarder les visites");
		System.out.println("4 - Se deconnecter");

		int choix = saisieInt("Choisir un menu");

		switch (choix) {
		case 1:
			updateAttente();
			break;
		case 2:
			showAttente();
			break;
		case 3:
			saveVisite();
			break;
		case 4:
			connected = null;
			menuPrincipal();
			break;

		}
		menuMedecin();

	}

	public static void addAttente() {
		System.out.println("Identifiant patient :");
		int id = saisieInt("Entrer id patient");
		Patient p = daoP.findById(id);
		if (p == null) {
			addPatient();
		} else {
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
		if(listedAttente.isEmpty()) {
			System.out.println("Il n'y a personne sur la liste d'attente");
		}
		else {
			System.out.println("Liste d'attente");
			for (Patient p : listedAttente) {
				System.out.println(p);
			}
		}
	}

	public static void updateAttente() {
		Double prix = saisieDouble("Prix de la consultation :");
		Visite v = new Visite(listedAttente.peek(), (Medecin) connected, prix, salle, LocalDate.now());
		listVisite.add(v);
		checkListVisite();
		listedAttente.poll();
		System.out.println("Le client suivant est " + listedAttente.peek());

	}

	public static void saveVisite() {
		for (Visite v : listVisite) {
			daoV.insert(v);
		}
		System.out.println("La liste des visiteurs a bien �t� sauvegard�e ");
		listVisite.clear();
	}

	public static void checkListVisite() {
		if (listVisite.size() == 10) {
			saveVisite();
			listVisite.clear(); // vider la liste des visites en local
		}

	}

	public static void saveAttente() {
		File f = new File("attente.txt");
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(listedAttente);
		}
		 catch (IOException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void openAttente() {
		File f = new File("attente.txt");
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			listedAttente =  (LinkedList<Patient>) ois.readObject();
			
		}
		 catch (IOException | ClassNotFoundException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void showVisite() {
		int id = saisieInt("saisir numero patient : ");
		daoV.findByPatient(id);
	}

}
