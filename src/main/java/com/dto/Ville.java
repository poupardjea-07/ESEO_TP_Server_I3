package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ville {

	private String codeCommune;
	private String nomCommune;
	private String codePostal;
	private String libelle;
	private String ligne;
	private String latitude;
	private String longitude;
}
