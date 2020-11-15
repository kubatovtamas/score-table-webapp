package com.csapatsportok.application.controller;

import com.csapatsportok.application.domain.Country;
import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.service.CountryService;
import com.csapatsportok.application.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class CountryController {

    private CountryService countryServ;
    @Autowired
    public void setCountryService(CountryService countryServ) { this.countryServ = countryServ; }

    private LeagueService leagueServ;
    @Autowired
    public void setLeagueService(LeagueService leagueServ) { this.leagueServ = leagueServ; }



    @RequestMapping("/")
    public String getAllCountries(Model model) {
        model.addAttribute("leagues", leagueServ.getAllLeagues());

        model.addAttribute("countries", countryServ.getAllCountries());
        model.addAttribute("maxNumOfLeagues", countryServ.getMaxNumOfLeagues());

        return "countries/countries";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editCountryById(Model model, @PathVariable("id") Optional<Long> id) throws RuntimeException {
        model.addAttribute("leagues", leagueServ.getAllLeagues());

        if (id.isPresent()) {
            /* Edit Existing */
            Country country = countryServ.getCountryById(id.get());
            model.addAttribute("country", country);
        } else {
            /* Add New */
            model.addAttribute("country", new Country());
        }

        return "countries/add_edit_country";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RuntimeException {
        countryServ.deleteCountryById(id);

        return "redirect:/";
    }

    @RequestMapping(path = "/createCountry", method = RequestMethod.POST)
    public String createOrUpdateEmployee(Country country) {
        /* Submit */
        countryServ.createOrUpdateCountry(country);
        return "redirect:/";
    }
}

