package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
