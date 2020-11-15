package com.csapatsportok.application.controller;

import com.csapatsportok.application.domain.Country;
import com.csapatsportok.application.domain.Team;
import com.csapatsportok.application.service.LeagueService;
import com.csapatsportok.application.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class TeamController {
    private LeagueService leagueServ;
    @Autowired
    public void setLeagueService(LeagueService leagueServ) { this.leagueServ = leagueServ; }

    private TeamService teamServ;
    @Autowired
    public void setTeamService(TeamService teamServ) {
        this.teamServ = teamServ;
    }



    @RequestMapping("/teams")
    public String getAllCountries(Model model) {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("teams", teamServ.getAllTeams());

        return "teams/teams";
    }

    @RequestMapping(path = {"/team/edit", "/team/edit/{id}"})
    public String editTeamById(Model model, @PathVariable("id") Optional<Long> id) throws RuntimeException {
        model.addAttribute("leagues", leagueServ.getAllLeagues());

        if (id.isPresent()) {
            /* Edit Existing */
            Team team = teamServ.getTeamById(id.get());
            model.addAttribute("team", team);
            model.addAttribute("existing", true);
        } else {
            /* Add New */
            model.addAttribute("team", new Team());
            model.addAttribute("existing", false);
        }

        return "teams/add_edit_team";
    }

    @RequestMapping(path = "/team/delete/{id}")
    public String deleteTeamById(Model model, @PathVariable("id") Long id) throws RuntimeException {
        teamServ.deleteTeamById(id);

        return "redirect:/teams";
    }

    @RequestMapping(path = "/team/createTeam", method = RequestMethod.POST)
    public String createOrUpdateTeam(Team team) {
        /* Submit */
        teamServ.createOrUpdateTeam(team);
        return "redirect:/teams";
    }
}
