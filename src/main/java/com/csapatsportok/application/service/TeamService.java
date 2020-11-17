package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.domain.Team;
import com.csapatsportok.application.repository.PlayerRepository;
import com.csapatsportok.application.repository.TeamRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class TeamService {

    private TeamRepository teamRepo;
    @Autowired
    public void setTeamRepo(TeamRepository teamRepo) {

        this.teamRepo = teamRepo;
    }



    public List<Team> getTeamsByLeague(League league) {
        Optional<List<Team>> team = teamRepo.findAllByLeague(league);

        return team.orElse(new ArrayList<Team>());
    }

    public List<Team> getAllTeams() {
        List<Team> teams = (List<Team>) teamRepo.findAll();

        if (teams.size() > 0) {
            return teams;
        } else {
            return new ArrayList<Team>();
        }
    }

    public Team getTeamById(Long id) throws RuntimeException {
        Optional<Team> team = teamRepo.findById(id);

        if (team.isPresent()) {
            return team.get();
        } else {
            throw new RuntimeException("Team with id: " + id + " not found");
        }
    }

    public void createOrUpdateTeam(Team entity) {
        if (entity.getId() == null) {
            /* Save New Entity */
            entity = teamRepo.save(entity);
        } else {
            /* Edit Existing Entity */
            Optional<Team> team = teamRepo.findById(entity.getId());

            if (team.isPresent()) {
                Team newEntity = team.get();
                newEntity.setName(entity.getName());
                newEntity.setLeague(entity.getLeague());
                newEntity.setPlayers(entity.getPlayers());

                newEntity = teamRepo.save(newEntity);
            } else {
                entity = teamRepo.save(entity);
            }
        }
    }

    public void deleteTeamById(Long id) throws RuntimeException {
        Optional<Team> team = teamRepo.findById(id);

        if(team.isPresent()) {
            for (Player p : team.get().getPlayers()) {
                p.setTeam(null);
            }
            teamRepo.deleteById(id);
        } else {
            throw new RuntimeException("Team with id: " + id + " not found");
        }
    }
}
