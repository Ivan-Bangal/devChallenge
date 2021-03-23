package com.night.countryapi.countryapi.repository;

import com.night.countryapi.countryapi.model.Country;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountrySortRepo extends PagingAndSortingRepository<Country, Long>  {
    
}
