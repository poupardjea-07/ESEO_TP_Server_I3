package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBlo;
import com.dto.Ville;

@RestController
public class VilleController {

	@Autowired
	VilleBlo villeService;

	@GetMapping(value = "/villes")
	public List<Ville> getAllVilles() {
		System.out.println("Get all villes");
		return villeService.getAllVilles();
	}
	
	@GetMapping(value = "/villes/nom/{nom}")
	public Ville getVilleByNom(@PathVariable String nom) {
		System.out.println("Get ville by nom");
		return villeService.getInfoVilleByNom(nom);
	}
	
	@GetMapping(value = "/villes/{code}")
	public Ville getVilleByCodeCommune(@PathVariable String code) {
		System.out.println("Get ville by code");
		return villeService.getInfoVilleByCode(code);
	}
	
	//ajouter une ville
    @PostMapping(value = "/villes")
    public void ajouterVille(@RequestBody Ville ville) {
    	villeService.addVille(ville);
    }
    
    //supprimer une ville
    @DeleteMapping(value = "/villes")
    public void deleteVille(@RequestBody String code) {
    	villeService.deleteVille(code);
    }
    
    //modifier une ville
    @PutMapping(value = "/villes")
    public void updateVille(@RequestBody Ville ville) {
    	villeService.updateVille(ville);
    }
	
	
	
	
	
	
}
