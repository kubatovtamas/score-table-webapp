package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.Country;
import com.csapatsportok.application.repository.CountryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class CountryService {

    private CountryRepository countryRepo;
    @Autowired
    public void setCountryRepo(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    public int getMaxNumOfLeagues() {
        return countryRepo.findMaxNumberOfLeagues();
    }

    public List<Country> getAllCountries() {
        List<Country> countries = (List<Country>) countryRepo.findAll();

        if (countries.size() > 0) {
            return countries;
        } else {
            return new ArrayList<Country>();
        }
    }

    public Country getCountryById(Long id) throws RuntimeException {
        Optional<Country> country = countryRepo.findById(id);

        if (country.isPresent()) {
            return country.get();
        } else {
            throw new RuntimeException("Country with id: " + id + " not found");
        }
    }

    public void createOrUpdateCountry(Country entity) {
        if (entity.getId() == null) {
            /* Save New Entity */
            entity = countryRepo.save(entity);

        } else {
            /* Edit Existing Entity */
            Optional<Country> country = countryRepo.findById(entity.getId());

            if (country.isPresent()) {
                Country newEntity = country.get();
                newEntity.setName(entity.getName());
                newEntity.setLeagues(entity.getLeagues());
                newEntity = countryRepo.save(newEntity);

            } else {
                entity = countryRepo.save(entity);

            }
        }
    }

    public void deleteCountryById(Long id) throws RuntimeException {
        Optional<Country> country = countryRepo.findById(id);

        if(country.isPresent()) {
            countryRepo.deleteById(id);
        } else {
            throw new RuntimeException("Country with id: " + id + " not found");
        }
    }
}
