package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBlo {
	
	public Ville getInfoVilleByCode(String code);
	public Ville getInfoVilleByNom(String nom);
	public List<Ville> getAllVilles();
	public void addVille(Ville ville);
	public void deleteVille(String code);
	public void updateVille(Ville ville);

}
