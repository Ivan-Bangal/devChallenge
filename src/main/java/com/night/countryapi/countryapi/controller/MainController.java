package com.night.countryapi.countryapi.controller;

import com.night.countryapi.countryapi.model.Continent;
import com.night.countryapi.countryapi.model.Country;
import com.night.countryapi.countryapi.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class MainController {

    @Autowired
    private CountryService service;
    
    @GetMapping()
    public String entry(Model model) {
        model.addAttribute("countries", service.getAllCountries());
        return "index";
    }

    @GetMapping("/create")
    public String createCountry(Model model) {
        model.addAttribute("country",new Country());
        model.addAttribute("continents", Continent.values());
        return "create";
    }

    @PostMapping("/save")
    public String saveCountry(Model model, @ModelAttribute Country country) {
        service.getRepo().save(country);
        return "redirect:/";
    }
    
    
    

}
