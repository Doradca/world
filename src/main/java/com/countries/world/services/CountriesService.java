package com.countries.world.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countries.world.model.entities.CountryLanguage;
import com.countries.world.repositories.CountryRepo;

@Service
public class CountriesService {
	private static final Logger logger = LoggerFactory.getLogger(CountriesService.class);

	@Autowired
	private CountryRepo countryRepo;

	public List<CountryLanguage> find(String countryCode) {
		logger.info("find: countryCode = " + countryCode);
		List<CountryLanguage> languages = this.countryRepo.findAllById(Arrays.asList(countryCode));
		logger.debug("find: founded languages size = " + languages.size());
		return languages;
	}
}
