package com.countries.world.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.countries.world.model.entities.CountryLanguage;

@Repository
public interface CountryRepo extends JpaRepository<CountryLanguage, String> {

}
