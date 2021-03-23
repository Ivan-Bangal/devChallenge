package com.night.countryapi.countryapi.repository;




import java.util.List;


import com.night.countryapi.countryapi.model.Country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends CrudRepository<Country, Long> {

    List<Country> findAll();

    List<Country> findByContinente(String continente);

    Country findById(long id);

    void deleteById(Long id);
    
}



