package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.*;
import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.repository.GoalRepository;
import com.csapatsportok.application.repository.LeagueRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class LeagueService {

    private LeagueRepository leagueRepo;
    @Autowired
    public void setLeagueRepo(LeagueRepository leagueRepo) { this.leagueRepo = leagueRepo; }


    public League getLeagueByName(String name) throws RuntimeException {
        Optional<League> league = leagueRepo.findByName(name);
        if (league.isPresent()) {
            return league.get();
        } else {
            throw new RuntimeException("League with name: " + name + " not found");
        }
    }

    public List<League> getAllLeagues() {
        List<League> leagues = (List<League>) leagueRepo.findAll();

        if (leagues.size() > 0) {
            return leagues;
        } else {
            return new ArrayList<League>();
        }
    }

    public League getLeagueById(Long id) throws RuntimeException {
        Optional<League> league = leagueRepo.findById(id);

        if (league.isPresent()) {
            return league.get();
        } else {
            throw new RuntimeException("League with id: " + id + " not found");
        }
    }

    public void createOrUpdateLeague(League entity) {
        if (entity.getId() == null) {
            /* Save New Entity */
            saveLeague(entity);
        } else {
            /* Edit Existing Entity */
            Optional<League> country = leagueRepo.findById(entity.getId());

            if (country.isPresent()) {
                League newEntity = country.get();
                newEntity.setCountry(entity.getCountry());
                newEntity.setName(entity.getName());
                newEntity.setTeams(entity.getTeams());

                saveLeague(newEntity);
            } else {
                saveLeague(entity);
            }
        }
    }

    public void deleteLeagueById(Long id) throws RuntimeException {
        Optional<League> league = leagueRepo.findById(id);

        if(league.isPresent()) {
            for (Team team : league.get().getTeams()) {
                for (Player player : team.getPlayers()) {
                    player.setTeam(null);
                }
            }
            leagueRepo.deleteById(id);
        } else {
            throw new RuntimeException("League with id: " + id + " not found");
        }
    }

    private void saveLeague(League entity) throws RuntimeException {
        try {
            leagueRepo.save(entity);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new RuntimeException("Duplicates not allowed for League Name");
        }
    }
}
