package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.domain.Team;
import com.csapatsportok.application.repository.LeagueRepository;
import com.csapatsportok.application.repository.PlayerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class PlayerService {

    private PlayerRepository playerRepo;
    @Autowired
    public void setPlayerRepo(PlayerRepository playerRepo) {

        this.playerRepo = playerRepo;
    }



    public List<Player> getAllPlayers() {
        List<Player> players = (List<Player>) playerRepo.findAll();

        if (players.size() > 0) {
            return players;
        } else {
            return new ArrayList<Player>();
        }
    }

    public Player getPlayerById(Long id) throws RuntimeException {
        Optional<Player> player = playerRepo.findById(id);

        if (player.isPresent()) {
            return player.get();
        } else {
            throw new RuntimeException("Player with id: " + id + " not found");
        }
    }

    public void createOrUpdatePlayer(Player entity) {
        if (entity.getId() == null) {
            /* Save New Entity */
            entity = playerRepo.save(entity);
        } else {
            /* Edit Existing Entity */
            Optional<Player> player = playerRepo.findById(entity.getId());

            if (player.isPresent()) {
                Player newEntity = player.get();
                newEntity.setName(entity.getName());
                newEntity.setTeam(entity.getTeam());

                newEntity = playerRepo.save(newEntity);
            } else {
                entity = playerRepo.save(entity);
            }
        }
    }

    public void deletePlayerById(Long id) throws RuntimeException {
        Optional<Player> player = playerRepo.findById(id);

        if(player.isPresent()) {
            playerRepo.deleteById(id);
        } else {
            throw new RuntimeException("Player with id: " + id + " not found");
        }
    }
}
