package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Admin;
import model.Adresse;
import model.Client;
import model.Fiche;
import model.Patient;
import model.Refuge;
import model.Vendeur;

public class DAOpatient implements IDAO<Patient,Integer>{

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
				p = new Patient(id, rs.getString("nom"), rs.getString("prenom"));
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
		
		
		return null;
	}

	@Override
	public void insert(Patient o) {
	
		
		
	}

	@Override
	public void update(Patient o) {
		
		
		
	}

	@Override
	public void delete(Integer id) {
		
		
		
	}

	

}
