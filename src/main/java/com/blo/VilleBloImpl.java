package com.blo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDao;
import com.dto.Ville;

@Service
public class VilleBloImpl implements VilleBlo{
	
	@Autowired
	private VilleDao villeDao;

	@Override
	public Ville getInfoVilles() {
		
		return villeDao.findVille();
		
	}
}
