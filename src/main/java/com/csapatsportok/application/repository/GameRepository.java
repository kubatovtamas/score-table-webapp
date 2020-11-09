package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findAll();
}
