package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    	
    	//String user = "root";
		//String password = "root";
		
		//myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_maven",user, password);
		
		String user = "eseo";
		String password = "eseo";
		
		myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/villes?autoReconnect=true&useSSL=false",user, password);
		
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
    
    public void addVille(Ville ville) {
    	try {
			DbConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = myConn.prepareStatement("INSERT INTO ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement,"
					+ "Ligne_5, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, ville.getCodeCommune());
			preparedStatement.setString(2, ville.getNomCommune());
			preparedStatement.setString(3, ville.getCodePostal());
			preparedStatement.setString(4, ville.getLibelle());
			preparedStatement.setString(5, ville.getLigne());
			preparedStatement.setString(6, ville.getLatitude());
			preparedStatement.setString(7, ville.getLongitude());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				System.out.println("échec de la création de la ville, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	
    }
    
    public void deleteVille(String code) {
    	try {
			DbConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = myConn.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE = ?");
			preparedStatement.setString(1, code);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				System.out.println("échec de la suppression de la ville, aucune ligne retirée dans la table.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	
    }
    
    
    public void updateVille(Ville ville) {
    	try {
			DbConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = myConn.prepareStatement("UPDATE ville_france SET Nom_commune = ?, Code_postal = ? WHERE Code_commune_INSEE = ?");
			preparedStatement.setString(1, ville.getNomCommune());
			preparedStatement.setString(2, ville.getCodePostal());
			preparedStatement.setString(3, ville.getCodeCommune());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				System.out.println("échec de la modification de la ville, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	
    }
	

}
