package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    @Override
    List<Country> findAll();

    @Query(value = """
            SELECT count(country_id) as c
            FROM LEAGUE
            group by country_id
            order by c desc
            limit 1
            """,nativeQuery = true)
    int findMaxNumberOfLeagues();
}
