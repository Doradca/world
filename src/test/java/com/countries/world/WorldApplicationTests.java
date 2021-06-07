package com.countries.world;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.countries.world.model.entities.CountryLanguage;
import com.countries.world.rest.CountriesCtrl;
import com.countries.world.services.CountriesService;

@SpringBootTest
class WorldApplicationTests {

	@Mock
	private CountriesService countriesService;

	@InjectMocks
	private CountriesCtrl countriesCtrl;

	private ResponseEntity<CountryLanguage> response;

	@DisplayName("Test find sth method")
	@Test
	void findSth() {
		Mockito.when(countriesService.find("BHR")).thenReturn(this.prepareSth());
		assertDoesNotThrow(() -> this.response = this.countriesCtrl.find("BHR"), "exception appeared");
		assertEquals(this.response.getStatusCode(), HttpStatus.OK, "bad response status");
		CountryLanguage obt = this.response.getBody();
		assertNotNull(obt, "nothing found");
		CountryLanguage exp = prepareSth().get(0);
		assertEquals(obt.getCountryCode(), exp.getCountryCode(), "returned with bad country code");
		assertEquals(obt.getLanguage(), exp.getLanguage(), "returned with bad language");
		assertEquals(obt.getPercentage(), exp.getPercentage(), "returned with bad percentage");
		assertEquals(obt.getIsOfficial(), exp.getIsOfficial(), "returned with bad isOfficial");
	}

	@DisplayName("Test find bad argument method")
	@Test
	void findWithBadArgument() {
		Mockito.when(countriesService.find("BHRXXXXX")).thenReturn(new LinkedList<>());
		assertDoesNotThrow(() -> this.response = this.countriesCtrl.find("BHRXXXXX"), "exception appeared");
		assertEquals(this.response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR, "bad response status");
		CountryLanguage obt = this.response.getBody();
		assertNull(obt, "found sth but null was expected");
	}

	private List<CountryLanguage> prepareSth() {
		CountryLanguage c = new CountryLanguage();
		c.setCountryCode("BHR");
		c.setLanguage("Arabic");
		c.setIsOfficial(true);
		c.setPercentage(67.7);
		List<CountryLanguage> l = new LinkedList<>();
		l.add(c);
		return l;
	}
}
