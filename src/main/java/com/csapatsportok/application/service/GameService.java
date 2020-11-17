package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.Game;
import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.domain.Team;
import com.csapatsportok.application.repository.CountryRepository;
import com.csapatsportok.application.repository.GameRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class GameService {

    private GameRepository gameRepo;
    @Autowired
    public void setGameRepo(GameRepository gameRepo) {

        this.gameRepo = gameRepo;
    }



    public List<Game> getAllGames() {
        List<Game> games = (List<Game>) gameRepo.findAll();

        if (games.size() > 0) {
            return games;
        } else {
            return new ArrayList<Game>();
        }
    }

    public Game getGameById(Long id) throws RuntimeException {
        Optional<Game> game = gameRepo.findById(id);

        if (game.isPresent()) {
            return game.get();
        } else {
            throw new RuntimeException("Game with id: " + id + " not found");
        }
    }

    public void createOrUpdateGame(Game entity) {
        if (entity.getId() == null) {
            /* Save New Entity */
            saveGame(entity);
        } else {
            /* Edit Existing Entity */
            Optional<Game> game = gameRepo.findById(entity.getId());

            if (game.isPresent()) {
                Game newEntity = game.get();
                newEntity.setHomeTeam(entity.getHomeTeam());
                newEntity.setAwayTeam(entity.getAwayTeam());
                newEntity.setNumHomeGoals(entity.getNumHomeGoals());
                newEntity.setNumAwayGoals(entity.getNumAwayGoals());
                newEntity.setDate(entity.getDate());

                saveGame(newEntity);
            } else {
                saveGame(entity);
            }
        }
    }

    public void deleteGameById(Long id) throws RuntimeException {
        Optional<Game> game = gameRepo.findById(id);

        if(game.isPresent()) {
            gameRepo.deleteById(id);
        } else {
            throw new RuntimeException("Game with id: " + id + " not found");
        }
    }

    private void saveGame(Game entity) throws RuntimeException {
        try {
            gameRepo.save(entity);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new RuntimeException("Duplicates not allowed for Home Team and Away Team. Two teams can only play two matches a season, each at home once.");
        }
    }
}
