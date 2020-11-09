package com.csapatsportok.application.service;

import com.csapatsportok.application.repository.LeagueRepository;
import com.csapatsportok.application.repository.PlayerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class PlayerService {

    private PlayerRepository playerRepo;
    @Autowired
    public void setPlayerRepo(PlayerRepository playerRepo) {

        this.playerRepo = playerRepo;
    }
}
