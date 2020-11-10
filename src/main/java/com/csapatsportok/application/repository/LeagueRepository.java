package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {
    League findByName(String name);
}
