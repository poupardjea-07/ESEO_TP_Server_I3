package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBlo {
	
	public Ville getInfoVilleByCode(String code);
	public Ville getInfoVilleByNom(String nom);
	public List<Ville> getAllVilles();

}
