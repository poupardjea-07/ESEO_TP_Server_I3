package com.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDaoImpl implements VilleDao{
	
	Connection myConn;
	
	//Connection à la base
	public void DbConnection() throws SQLException {
    	
    	String user = "root";
		String password = "root";
		
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_maven",user, password);
		
		System.out.println("connection to the DB succesfull "+ user);
    }
	
	//trouve une ville par son code unique
	@Override
	public Ville findVilleByCode(String code) {
		
		try {
			DbConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet res = null;
		Ville ville = null;
		try {
			preparedStatement = myConn.prepareStatement("SELECT * FROM ville_france where Code_commune_INSEE = ?");
			preparedStatement.setString(1, code);
			res = preparedStatement.executeQuery();
			if (res.next()) {
				ville = new Ville(res.getString("Code_commune_INSEE"), res.getString("Nom_commune"), res.getString("Code_postal"), 
						res.getString("Libelle_acheminement"), res.getString("Ligne_5"), res.getString("Latitude"),res.getString("Longitude"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return ville;
	}
	
	//trouve une ville par son nom
	@Override
	public Ville findVilleByNom(String nom) {
		
		try {
			DbConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet res = null;
		Ville ville = null;
		try {
			preparedStatement = myConn.prepareStatement("SELECT * FROM ville_france where Nom_commune = ?");
			preparedStatement.setString(1, nom);
			res = preparedStatement.executeQuery();
			if (res.next()) {
				ville = new Ville(res.getString("Code_commune_INSEE"), res.getString("Nom_commune"), res.getString("Code_postal"), 
						res.getString("Libelle_acheminement"), res.getString("Ligne_5"), res.getString("Latitude"),res.getString("Longitude"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return ville;
	}


    // get toutes les villes
    public List<Ville> getAllRows(){
    	
    	try {
			DbConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Statement myStmt = null;
		ResultSet res = null;
		List<Ville> villes = new ArrayList<Ville>();
		
		try {
			myStmt = myConn.createStatement();
			res = myStmt.executeQuery("SELECT * FROM ville_france");
			while(res.next()) {
				villes.add(new Ville(res.getString("Code_commune_INSEE"), res.getString("Nom_commune"), res.getString("Code_postal"), 
						res.getString("Libelle_acheminement"), res.getString("Ligne_5"), res.getString("Latitude"),res.getString("Longitude"))); 
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return villes;
	}
	

}
