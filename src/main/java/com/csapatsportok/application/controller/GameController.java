package com.csapatsportok.application.controller;

import com.csapatsportok.application.domain.Game;
import com.csapatsportok.application.domain.Player;
import com.csapatsportok.application.service.GameService;
import com.csapatsportok.application.service.LeagueService;
import com.csapatsportok.application.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class GameController {

    private LeagueService leagueServ;
    @Autowired
    public void setLeagueService(LeagueService leagueServ) { this.leagueServ = leagueServ; }

    private GameService gameServ;
    @Autowired
    public void setGameService(GameService gameServ) { this.gameServ = gameServ; }

    private TeamService teamServ;
    @Autowired
    public void setTeamService(TeamService teamServ) {
        this.teamServ = teamServ;
    }


    @RequestMapping("/games")
    public String getAllGames(Model model) {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("games", gameServ.getAllGames());

        return "games/games";
    }

    @RequestMapping(path = {"/game/edit", "/game/edit/{id}"})
    public String editGameById(Model model, @PathVariable("id") Optional<Long> id) throws RuntimeException {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("teams", teamServ.getAllTeams());

        if (id.isPresent()) {
            /* Edit Existing */
            Game game = gameServ.getGameById(id.get());
            model.addAttribute("game", game);
            model.addAttribute("existing", true);
        } else {
            /* Add New */
            model.addAttribute("game", new Game());
            model.addAttribute("existing", false);
        }

        return "games/add_edit_game";
    }

    @RequestMapping(path = "/game/delete/{id}")
    public String deleteGameById(Model model, @PathVariable("id") Long id) throws RuntimeException {
        gameServ.deleteGameById(id);

        return "redirect:/games";
    }

    @RequestMapping(path = "/game/createGame", method = RequestMethod.POST)
    public String createOrUpdateGame(Game game) {
        /* Submit */
        gameServ.createOrUpdateGame(game);
        return "redirect:/games";
    }
}
