package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.Country;
import com.csapatsportok.application.repository.CountryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CountryService {

    private CountryRepository countryRepo;

    @Autowired
    public void setCountryRepo(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    public List<Country> getCountries() {
        return countryRepo.findAll();
    }

    public int getMaxNumOfLeagues() {
        return countryRepo.findMaxNumberOfLeagues();
    }
}
