package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Visite;



public class DAOVisite implements IDAO<Visite,Integer> {

	@Override
	public Visite findById(Integer numero) {
		Visite v=null;
		DAOCompte daoC = new DAOCompte();
		DAOPatient daoP = new DAOPatient();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * from visite where numero=?");
			ps.setInt(1,numero);

			ResultSet rs = ps.executeQuery();



			while(rs.next()) 
			{
				Compte c = daoC.findById(rs.getInt("id"));
				Patient p = (Patient) daoP.findById(rs.getInt("id"));
				v = new Visite(numero,p,c,rs.getDouble("prix"),rs.getInt("salle"), LocalDate.parse(rs.getString("date_visite")));
			}

			rs.close();
			ps.close();
			conn.close();


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
		return v;
	}

	@Override
	public List<Visite> findAll() {
		List<Visite> visites = new ArrayList<>();
		DAOCompte daoC = new DAOCompte();
		DAOPatient daoP = new DAOPatient();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * from visite");
			ResultSet rs = ps.executeQuery();

			Visite v=null;

			while(rs.next()) 
			{
				Compte c = daoC.findById(rs.getInt("id"));
				Patient p = (Patient) daoP.findById(rs.getInt("id"));
				v = new Visite(rs.getInt("numero"),p,c,rs.getDouble("prix"),rs.getInt("salle"), LocalDate.parse(rs.getString("date_visite")));

				visites.add(v);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return visites;
	}

	@Override
	public void insert(Visite v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO visite (id_patient,id_medecin,prix,salle,date_visite) VALUES (?,?,?,?,?)");

			System.out.println("Insertion des caracteristiques de la visite");
			ps.setInt(1, v.getPatient().getId());
			ps.setInt(2, v.getMedecin().getId());
			ps.setDouble(3, v.getPrix());
			ps.setInt(4, v.getSalle());
			ps.setString(5, v.getDateVisite().toString());


			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}

		System.out.println("Visite"+v+" inseree avec succes");	
		
	}

	@Override
	public void update(Visite v) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("UPDATE visite id_patient=?, id_medecin=?, prix=?, salle=?, date_visite=? where numero=?");

			System.out.println("Update de la visite");
			ps.setInt(1, v.getPatient().getId());
			ps.setInt(2, v.getMedecin().getId());
			ps.setDouble(3, v.getPrix());
			ps.setInt(4, v.getSalle());
			ps.setString(5, v.getDateVisite().toString());

			ps.setInt(6, v.getNumero());


			ps.executeUpdate();
			ps.close();
			conn.close();


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Visite"+v+" update");


	}

	@Override
	public void delete(Integer numero) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM visite WHERE numero=?");
			ps.setInt(1,numero);

			ps.executeUpdate();


			ps.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
}
