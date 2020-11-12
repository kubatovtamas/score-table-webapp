package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.domain.Team;
import com.csapatsportok.application.repository.PlayerRepository;
import com.csapatsportok.application.repository.TeamRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TeamService {

    private TeamRepository teamRepo;
    @Autowired
    public void setTeamRepo(TeamRepository teamRepo) {

        this.teamRepo = teamRepo;
    }

    public List<Team> getTeamsByLeague(League league) {
        return teamRepo.findAllByLeague(league);
    }

    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }
}
