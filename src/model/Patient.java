package model;

public class Patient {
		
		private static Integer id;
		private String nom;
		private String prenom;
		
		public Patient(String nom, String prenom) {
	
			id++;
			this.nom = nom;
			this.prenom = prenom;
		}

		

		public static Integer getId() {
			return id;
		}



		public static void setId(Integer id) {
			Patient.id = id;
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
