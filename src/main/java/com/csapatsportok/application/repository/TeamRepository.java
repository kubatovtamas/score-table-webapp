package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<List<Team>> findAllByLeague(League league);
}
