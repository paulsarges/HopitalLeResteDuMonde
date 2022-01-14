package model;

public class Patient {
		
		Integer idPatient;
		String nomPatient;
		String prenomPatient;
		
		public Patient(Integer idPatient, String nomPatient, String prenomPatient) {
	
			this.idPatient = idPatient;
			this.nomPatient = nomPatient;
			this.prenomPatient = prenomPatient;
		}

		public Integer getIdPatient() {
			return idPatient;
		}

		public void setIdPatient(Integer idPatient) {
			this.idPatient = idPatient;
		}

		public String getNomPatient() {
			return nomPatient;
		}

		public void setNomPatient(String nomPatient) {
			this.nomPatient = nomPatient;
		}

		public String getPrenomPatient() {
			return prenomPatient;
		}

		public void setPrenomPatient(String prenomPatient) {
			this.prenomPatient = prenomPatient;
		}

		@Override
		public String toString() {
			return "Patient [idPatient=" + idPatient + ", nomPatient=" + nomPatient + ", prenomPatient=" + prenomPatient
					+ "]";
		}
		
	

}
