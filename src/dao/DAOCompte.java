package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Compte;

public class DAOCompte implements IDAO<Compte,Integer> {

	@Override
	public Compte findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Compte o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Compte o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	public static Compte seConnecter(String login,String password) 
	{
		Compte connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital?characterEncoding=UTF-8","root","");

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login=? and password=?");
			ps.setString(1,login);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("Medecin")) {
					// connect = new Medecin();
				}

				/*if(rs.getString("type_compte").equals("client")) 
				{
					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					connect=new Client(rs.getInt("id_compte"),rs.getString("login"), rs.getString("password"), rs.getString("mail"), rs.getString("tel"), a);
				}
				else if(rs.getString("type_compte").equals("admin")) 
				{
					connect = new Admin(rs.getInt("id_compte"),rs.getString("login"), rs.getString("password"),rs.getString("mail"));
				}
				else if(rs.getString("type_compte").equals("vendeur")) 
				{
					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					connect=new Vendeur(rs.getInt("id_compte"),rs.getString("login"), rs.getString("password"), rs.getString("mail"), Refuge.valueOf(rs.getString("refuge")), a);
				}*/
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connect;

	}

}
