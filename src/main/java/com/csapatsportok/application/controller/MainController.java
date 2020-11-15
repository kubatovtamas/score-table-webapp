package com.csapatsportok.application.controller;

import com.csapatsportok.application.info.GoalDifferenceInfo;
import com.csapatsportok.application.info.MatchInfo;
import com.csapatsportok.application.info.ScorerInfo;
import com.csapatsportok.application.info.TableInfo;
import com.csapatsportok.application.domain.*;
import com.csapatsportok.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private CountryService countryServ;
    @Autowired
    public void setCountryService(CountryService countryServ) { this.countryServ = countryServ; }

    private GameService gameServ;
    @Autowired
    public void setGameService(GameService gameServ) { this.gameServ = gameServ; }

    private GoalService goalServ;
    @Autowired
    public void setGoalService(GoalService goalServ) { this.goalServ = goalServ; }

    private LeagueService leagueServ;
    @Autowired
    public void setLeagueService(LeagueService leagueServ) { this.leagueServ = leagueServ; }

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











    @RequestMapping("/goals")
    public String allGoals(Model model) {
        List<Goal> goals = goalServ.getAllGoals();
        model.addAttribute("goals", goals);

        return "goals";
    }
}
