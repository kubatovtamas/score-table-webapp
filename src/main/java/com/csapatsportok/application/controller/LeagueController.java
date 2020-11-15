package com.csapatsportok.application.controller;


import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.info.GoalDifferenceInfo;
import com.csapatsportok.application.info.MatchInfo;
import com.csapatsportok.application.info.ScorerInfo;
import com.csapatsportok.application.info.TableInfo;
import com.csapatsportok.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class LeagueController {
    private CountryService countryServ;
    @Autowired
    public void setCountryService(CountryService countryServ) { this.countryServ = countryServ; }

    private LeagueService leagueServ;
    @Autowired
    public void setLeagueService(LeagueService leagueServ) { this.leagueServ = leagueServ; }

    private TeamService teamServ;
    @Autowired
    public void setTeamService(TeamService teamServ) {
        this.teamServ = teamServ;
    }

    private TableInfoService tableInfoService;
    @Autowired
    public void setTableInfoService(TableInfoService tableInfoService) { this.tableInfoService = tableInfoService; }

    private MatchInfoService matchInfoService;
    @Autowired
    public void setMatchInfoService(MatchInfoService matchInfoService) { this.matchInfoService = matchInfoService; }

    private ScorerInfoService scorerInfoService;
    @Autowired
    public void setScorerInfoService(ScorerInfoService scorerInfoService) {
        this.scorerInfoService = scorerInfoService;
    }

    private GoalDifferenceInfoService goalDifferenceInfoService;
    @Autowired
    public void setGoalDifferenceInfoService(GoalDifferenceInfoService goalDifferenceInfoService) {
        this.goalDifferenceInfoService = goalDifferenceInfoService;
    }



    @RequestMapping("/leagues")
    public String allLeagues(Model model) {
        model.addAttribute("leagues", leagueServ.getAllLeagues());

        return "leagues/leagues";
    }

    @RequestMapping("/league/{name}")
    public String league(@PathVariable(value = "name") String name, Model model) {
        model.addAttribute("leagues", leagueServ.getAllLeagues());

        League league = leagueServ.getLeagueByName(name);
        List<TableInfo> tableInfos = tableInfoService.getTableInfoByLeagueName(league.getName());
        model.addAttribute("results", tableInfos);
        List<MatchInfo> matchInfos = matchInfoService.getMatchinfoByLeagueName(league.getName());
        model.addAttribute("matches", matchInfos);
        List<ScorerInfo> scorerInfos = scorerInfoService.getScorerInfoByLeagueName(league.getName());
        model.addAttribute("scorers", scorerInfos);
        List<GoalDifferenceInfo> goalDifferenceInfos = goalDifferenceInfoService.getGoalDifferenceInfoByLeagueName(league.getName());
        model.addAttribute("goalDiffs", goalDifferenceInfos);
        model.addAttribute("teams", teamServ.getTeamsByLeague(league));

        return "leagues/league";
    }

    @RequestMapping(path = {"/league/edit", "/league/edit/{id}"})
    public String editLeagueById(Model model, @PathVariable("id") Optional<Long> id) throws RuntimeException {
        model.addAttribute("leagues", leagueServ.getAllLeagues());
        model.addAttribute("countries", countryServ.getAllCountries());

        if (id.isPresent()) {
            /* Edit Existing */
            League league = leagueServ.getLeagueById(id.get());
            model.addAttribute("league", league);
        } else {
            /* Add New */
            model.addAttribute("league", new League());
        }

        return "leagues/add_edit_league";
    }

    @RequestMapping(path = "/league/delete/{id}")
    public String deleteLeagueById(Model model, @PathVariable("id") Long id) throws RuntimeException {
        leagueServ.deleteLeagueById(id);

        return "redirect:/leagues";
    }

    @RequestMapping(path = "/league/createLeague", method = RequestMethod.POST)
    public String createOrUpdateLeague(League league) {
        /* Submit */
        leagueServ.createOrUpdateLeague(league);
        return "redirect:/leagues";
    }
}
