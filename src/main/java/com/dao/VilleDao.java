package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDao {

	public Ville findVilleByCode(String code);
	public Ville findVilleByNom(String nom);
	public List<Ville> getAllRows();
	public void addVille(Ville ville);
	public void deleteVille(String code);
	public void updateVille(Ville ville);
}
