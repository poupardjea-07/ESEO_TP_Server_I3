package com.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDao;
import com.dto.Ville;

@Service
public class VilleBloImpl implements VilleBlo{
	
	@Autowired
	private VilleDao villeDao;

	@Override
	public Ville getInfoVilleByCode(String code) {
		
		Ville ville = villeDao.findVilleByCode(code);
		return ville;
		
	}
	
	@Override
	public Ville getInfoVilleByNom(String nom) {
		
		Ville ville = villeDao.findVilleByNom(nom);
		return ville;
		
	}
	
	@Override
	public List<Ville> getAllVilles() {
		
		List<Ville> villes = villeDao.getAllRows();
		return villes;
		
	}
	
	@Override
	public void addVille(Ville ville) {
		
		villeDao.addVille(ville);
		
	}

	@Override
	public void deleteVille(String code) {
		villeDao.deleteVille(code);
		
	}

	@Override
	public void updateVille(Ville ville) {
		villeDao.updateVille(ville);
		
	}
}
