package model;

public class Medecin extends Compte {

	public Medecin(Integer id, String login, String password, String typeCompte) {
		super(id, login, password, typeCompte);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Medecin [id=" + id + ", login=" + login + ", password=" + password + ", typeCompte=" + typeCompte + "]";
	}

	
	
}
