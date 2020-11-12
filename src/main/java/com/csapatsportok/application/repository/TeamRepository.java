package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll();

    List<Team> findAllByLeague(League league);
}
