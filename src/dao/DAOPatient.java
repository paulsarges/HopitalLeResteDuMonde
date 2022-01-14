package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Patient;

public class DAOPatient implements IDAO<Patient, Integer>{


	@Override
	public Patient findById(Integer id) {
		
		Patient p=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * from patient where id_patient=?");
			ps.setInt(1,id);


			ResultSet rs = ps.executeQuery();



			while(rs.next()) 
			{
				p = new Patient(rs.getString("nom"), rs.getString("prenom"));
			}
			rs.close();
			ps.close();
			conn.close();


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return p;

			}

	@Override
	public List<Patient> findAll() {
		List<Patient> patients = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/safarimeetic?characterEncoding=UTF-8","root","");

			PreparedStatement ps = conn.prepareStatement("SELECT * from patients");
			ResultSet rs = ps.executeQuery();

			Patient p=null;

			while(rs.next()) 
			{
				
				p = new Patient(rs.getString("nom"), rs.getString("prenom"));

				patients.add(p);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return patients;
		
	}

	@Override
	public void insert(Patient p) {
	
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/safarimeetic?characterEncoding=UTF-8","root","");
		
			PreparedStatement ps = conn.prepareStatement("INSERT INTO patient (id,nom,prenom) VALUES (?,?,?)");

			System.out.println("Insertion des caractéristiques de la fiche");
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNom());
			ps.setString(3, p.getPrenom());


			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}

		System.out.println("Fiche"+p+" insérée avec succès");	
		
		
	}

	@Override
	public void update(Patient p) {
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/safarimeetic?characterEncoding=UTF-8","root","");

			PreparedStatement ps = conn.prepareStatement("UPDATE patient set nom=?, prenom=?  where id=?");

			System.out.println("Update de la fiche");
			ps.setString(1, p.getNom());
			ps.setString(1, p.getPrenom());
			ps.setInt(3, p.getId());


			ps.executeUpdate();
			ps.close();
			conn.close();


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Fiche"+p+" update");


		
	}

	@Override
	public void delete(Integer id) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/safarimeetic?characterEncoding=UTF-8","root","");
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM patient WHERE id=?");
			ps.setInt(1,id);

			ps.executeUpdate();


			ps.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		
	}

	

}

