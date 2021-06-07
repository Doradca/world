package com.countries.world.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CountryLanguage {
	@Id
	private String countryCode;
	private String language;
	private Boolean isOfficial;
	private Double percentage;
}
