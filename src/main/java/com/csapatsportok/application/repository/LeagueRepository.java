package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {
    League findByName(String name);

    List<League> findAll();
}
