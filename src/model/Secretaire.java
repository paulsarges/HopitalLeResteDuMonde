package model;

public class Secretaire extends Compte {

	public Secretaire(Integer id, String login, String password, String typeCompte) {
		super(id, login, password, typeCompte);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Secretaire [id=" + id + ", login=" + login + ", password=" + password + ", typeCompte=" + typeCompte
				+ "]";
	}

	
	
}
