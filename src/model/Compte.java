package model;

public abstract class Compte {
	
	protected Integer id;
	protected String login;
	protected String password; 
	protected String typeCompte;
	
	
	public Compte(Integer id,String login, String password, String typeCompte) {
		this.id=id;
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

}
