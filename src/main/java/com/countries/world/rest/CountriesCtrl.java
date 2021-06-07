package com.countries.world.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countries.world.model.entities.CountryLanguage;
import com.countries.world.services.CountriesService;

@RestController
public class CountriesCtrl {
	private static final Logger logger = LoggerFactory.getLogger(CountriesCtrl.class);

	@Autowired
	private CountriesService countriesService;

	@GetMapping
	public ResponseEntity<CountryLanguage> find(@RequestParam(value = "countryCode") String countryCode) {
		logger.info("find: countryCode = " + countryCode);
		List<CountryLanguage> languages = this.countriesService.find(countryCode);
		if (languages != null && ! languages.isEmpty()) {
			logger.debug("find: founded example: language.language = " + languages.get(0).getLanguage());
			return new ResponseEntity<>(languages.get(0), HttpStatus.OK);
		} else {
			logger.debug("find: founded no language");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
