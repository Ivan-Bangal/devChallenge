package com.night.countryapi.countryapi.service;

import java.util.List;

import com.night.countryapi.countryapi.model.Country;
import com.night.countryapi.countryapi.repository.CountryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CountryService {
    
    @Autowired
    private CountryRepo repo;

    public CountryRepo getRepo(){
        return repo;
    }


    public Country findById(long id){
        return repo.findById(id);
    }


    public List<Country> getAllCountries(){
        return repo.findAll();
    }



}
