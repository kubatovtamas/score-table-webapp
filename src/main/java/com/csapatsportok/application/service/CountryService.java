package com.csapatsportok.application.service;

import com.csapatsportok.application.repository.CountryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class CountryService {

    private CountryRepository countryRepo;
    @Autowired
    public void setCountryRepo(CountryRepository countryRepo) {

        this.countryRepo = countryRepo;
    }

}
