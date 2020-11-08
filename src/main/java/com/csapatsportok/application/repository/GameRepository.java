package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
