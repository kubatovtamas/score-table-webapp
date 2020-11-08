package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.League;
import org.springframework.data.repository.CrudRepository;

public interface LeagueRepository extends CrudRepository<League, Long> {
}
