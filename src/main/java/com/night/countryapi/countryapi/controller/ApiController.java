package com.night.countryapi.countryapi.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.night.countryapi.countryapi.errors.NotFoundException;
import com.night.countryapi.countryapi.model.Country;
import com.night.countryapi.countryapi.repository.CountryRepo;
import com.night.countryapi.countryapi.repository.CountrySortRepo;
import com.night.countryapi.countryapi.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CountryService service;

    @Autowired
    private CountrySortRepo repo;

    @GetMapping("/{id}")
    public Country getById(@PathVariable("id") Long id) {
        Country requestedCountry = service.findById(id);

        if (requestedCountry == null) {
            throw new NotFoundException("Not Found");
        } else {
            return requestedCountry;
        }
    }

    @GetMapping("countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        try {
            List<Country> items = new ArrayList<Country>();

            service.getRepo().findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createCountry")
    public ResponseEntity<Country> create(@RequestBody Country item) {
        try {
            if(!item.hasNullValues()){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);    
            }
            
            Country saved = service.getRepo().save(item);
            
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/updateCountry/{id}")
    public ResponseEntity<Country> update(@PathVariable("id") long id, @RequestBody(required = false) Country item) {
        if (item == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Country existingItem = service.getRepo().findById(id);
        if (existingItem != null) {
            existingItem.updateAtributes(item);
            return new ResponseEntity<>(service.getRepo().save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sortedCountries")
    public ResponseEntity<Iterator<Country>> getCountriesSorted(
            @RequestBody(required = false) String[] sortingParameters) {
        try {
            List<Country> items = new ArrayList<Country>();
            Iterator<Country> list = null;

            service.getRepo().findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            Iterable<Country> sort = repo.findAll(Sort.by(sortingParameters));

            if(sortingParameters.length == 0){
                list = items.iterator();
            }else{
                list = sort.iterator();
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCountry/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            service.getRepo().deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
