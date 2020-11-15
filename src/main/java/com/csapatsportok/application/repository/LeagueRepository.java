package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {

    Optional<League> findByName(String name);
}
