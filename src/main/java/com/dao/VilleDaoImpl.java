package com.dao;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDaoImpl implements VilleDao{

	@Override
	public Ville findVille() {
		Ville ville = new Ville();
		ville.setCodeCommune("plop");
		ville.setCommune("plpi");
		return ville;
	}

}
