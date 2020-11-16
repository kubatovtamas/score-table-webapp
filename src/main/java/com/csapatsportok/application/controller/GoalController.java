package com.csapatsportok.application.controller;

import com.csapatsportok.application.domain.Game;
import com.csapatsportok.application.domain.Goal;
import com.csapatsportok.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class GoalController {
    private LeagueService leagueServ;
    @Autowired
    public void setLeagueService(LeagueService leagueServ) { this.leagueServ = leagueServ; }

    private GoalService goalServ;
    @Autowired
    public void setGoalService(GoalService goalServ) { this.goalServ = goalServ; }

    private GameService gameServ;
    @Autowired
    public void setGameService(GameService gameServ) { this.gameServ = gameServ; }

    private PlayerService playerServ;
    @Autowired
    public void setPlayerService(PlayerService playerServ) {
        this.playerServ = playerServ;
    }

    private TeamService teamServ;
    @Autowired
    public void setTeamService(TeamService teamServ) {
        this.teamServ = teamServ;
    }



    @RequestMapping("/goals")
    public String getAllGames(Model model) {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("goals", goalServ.getAllGoals());

        return "goals/goals";
    }

    @RequestMapping(path = {"/goal/edit", "/goal/edit/{id}"})
    public String editGoalById(Model model, @PathVariable("id") Optional<Long> id) throws RuntimeException {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("games", gameServ.getAllGames());
        model.addAttribute("teams", teamServ.getAllTeams());
        model.addAttribute("players", playerServ.getAllPlayers());

        if (id.isPresent()) {
            /* Edit Existing */
            Goal goal = goalServ.getGoalById(id.get());
            model.addAttribute("goal", goal);
            model.addAttribute("existing", true);
        } else {
            /* Add New */
            model.addAttribute("goal", new Goal());
            model.addAttribute("existing", false);
        }

        return "goals/add_edit_goal";
    }

    @RequestMapping(path = "/goal/delete/{id}")
    public String deleteGoalById(Model model, @PathVariable("id") Long id) throws RuntimeException {
        goalServ.deleteGoalById(id);

        return "redirect:/goals";
    }

    @RequestMapping(path = "/goal/createGoal", method = RequestMethod.POST)
    public String createOrUpdateGoal(Goal goal) {
        /* Submit */
        goalServ.createOrUpdateGoal(goal);
        return "redirect:/goals";
    }
}
