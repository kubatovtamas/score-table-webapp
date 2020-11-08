package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
