package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.Game;
import com.csapatsportok.application.repository.CountryRepository;
import com.csapatsportok.application.repository.GameRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class GameService {

    private GameRepository gameRepo;
    @Autowired
    public void setGameRepo(GameRepository gameRepo) {

        this.gameRepo = gameRepo;
    }

    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }
}
