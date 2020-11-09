package com.csapatsportok.application.service;

import com.csapatsportok.application.repository.CountryRepository;
import com.csapatsportok.application.repository.GameRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class GameService {

    private GameRepository gameRepo;
    @Autowired
    public void setGameRepo(GameRepository gameRepo) {

        this.gameRepo = gameRepo;
    }
}
