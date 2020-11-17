package com.csapatsportok.application.controller;

import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.domain.Team;
import com.csapatsportok.application.service.LeagueService;
import com.csapatsportok.application.service.PlayerService;
import com.csapatsportok.application.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class PlayerController {
    private LeagueService leagueServ;
    @Autowired
    public void setLeagueService(LeagueService leagueServ) { this.leagueServ = leagueServ; }

    private TeamService teamServ;
    @Autowired
    public void setTeamService(TeamService teamServ) {
        this.teamServ = teamServ;
    }

    private PlayerService playerServ;
    @Autowired
    public void setPlayerService(PlayerService playerServ) {
        this.playerServ = playerServ;
    }



    @RequestMapping("/players")
    public String getAllPlayers(Model model) {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("players", playerServ.getAllPlayers());

        return "players/players";
    }

    @RequestMapping(path = {"/player/edit", "/player/edit/{id}"})
    public String editPlayerById(Model model, @PathVariable("id") Optional<Long> id) throws RuntimeException {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("teams", teamServ.getAllTeams());

        if (id.isPresent()) {
            /* Edit Existing */
            Player player = playerServ.getPlayerById(id.get());
            model.addAttribute("player", player);
            model.addAttribute("existing", true);
            model.addAttribute("freeAgent", (player.getTeam() == null));
        } else {
            /* Add New */
            model.addAttribute("player", new Player());
            model.addAttribute("existing", false);
            model.addAttribute("freeAgent", false);
        }

        return "players/add_edit_player";
    }

    @RequestMapping(path = "/player/delete/{id}")
    public String deletePlayerById(Model model, @PathVariable("id") Long id) throws RuntimeException {
        playerServ.deletePlayerById(id);

        return "redirect:/players";
    }

    @RequestMapping(path = "/player/createPlayer", method = RequestMethod.POST)
    public String createOrUpdatePlayer(Player player) {
        /* Submit */
        playerServ.createOrUpdatePlayer(player);
        return "redirect:/players";
    }
}