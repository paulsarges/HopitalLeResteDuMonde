package model;

public class Medecin extends Compte {

	public Medecin(Integer id, String login, String password) {
		super(id, login, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Medecin [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
	
	
}
