package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAllByTeam(Team team);
}
