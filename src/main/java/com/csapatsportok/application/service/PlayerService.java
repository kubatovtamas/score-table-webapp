package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.repository.LeagueRepository;
import com.csapatsportok.application.repository.PlayerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PlayerService {

    private PlayerRepository playerRepo;
    @Autowired
    public void setPlayerRepo(PlayerRepository playerRepo) {

        this.playerRepo = playerRepo;
    }

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }
}
