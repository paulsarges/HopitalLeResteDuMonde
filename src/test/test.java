package test;

import java.util.Scanner;

import model.Secretaire;
import model.Medecin;
import model.Client;

public class test {
	
	
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
	
	public static void menuMedecin(boolean pause) {

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
		menuMedecin(false);

	}
	
	public static void addAttente() {
		System.out.println("Identifiant patient :");
		
	}
	
	public static void showAttente() {
		System.out.println("Liste d'attente");
	}
	
	public static void updateAttente() {
		System.out.println("Le client suivant est");
	}
	
	public static void saveVisite() {
		System.out.println("La liste des visiteurs a bien été sauvegardée");
	}
	
	

}
