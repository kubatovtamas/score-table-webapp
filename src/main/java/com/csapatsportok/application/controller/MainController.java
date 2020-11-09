package com.csapatsportok.application.controller;

import com.csapatsportok.application.domain.Country;
import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.domain.Team;
import com.csapatsportok.application.repository.CountryRepository;
import com.csapatsportok.application.service.CountryService;
import com.csapatsportok.application.service.PlayerService;
import com.csapatsportok.application.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

//    private CountryService countryServ;
//    @Autowired
//    public void setCountryService(CountryService countryServ) {
//        this.countryServ = countryServ;
//    }

    private TeamService teamServ;
    @Autowired
    public void setCountryService(TeamService teamServ) {
        this.teamServ = teamServ;
    }

    private PlayerService playerServ;
    @Autowired
    public void setPlayerService(PlayerService playerServ) {
        this.playerServ = playerServ;
    }

    private HashMap<Team, List<Player>> getRosters() {
        List<Team> teams = teamServ.getTeamRepo().findAll();
        HashMap<Team, List<Player>> rosters = new HashMap<Team, List<Player>>();
        for (Team team : teams) {
            List<Player> players = playerServ.getPlayerRepo().findAllByTeam(team);
            rosters.put(team, players);
        }
        return rosters;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTeamPlayerInfo() {
        return ResponseEntity.ok(getRosters());
    }
}
