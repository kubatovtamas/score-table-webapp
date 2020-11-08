package com.csapatsportok.application.controller;

import com.csapatsportok.application.domain.Country;
import com.csapatsportok.application.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {






    // test
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Country n = new Country(name);
        countryRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Country> getAllUsers() {
        // This returns a JSON or XML with the users
        return countryRepository.findAll();
    }
}
