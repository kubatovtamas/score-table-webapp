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
    public void setGameService(GoalService goalServ) { this.goalServ = goalServ; }

    private LeagueService leagueServ;
    @Autowired
    public void setGameService(LeagueService leagueServ) { this.leagueServ = leagueServ; }

    private PlayerService playerServ;
    @Autowired
    public void setPlayerService(PlayerService playerServ) {
        this.playerServ = playerServ;
    }

    private TeamService teamServ;
    @Autowired
    public void setCountryService(TeamService teamServ) {
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



















    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("countries", countryServ.getCountries());
        model.addAttribute("maxNumOfLeagues", countryServ.getMaxNumOfLeagues());
        return "index";
    }

    @RequestMapping("/league/{name}")
    public String league(@PathVariable(value = "name") String name, Model model) {
        League league = leagueServ.getLeagueByName(name);
        List<TableInfo> tableInfos = tableInfoService.getTableInfoByLeagueName(league.getName());
        model.addAttribute("teams", teamServ.getTeamsByLeague(league));
        model.addAttribute("table", tableInfos);
        return "league";
    }





    /* TABLE */
    @RequestMapping("/testtable")
    public String test1(Model model) {
        List<TableInfo> tableInfos = tableInfoService.getTableInfoByLeagueName("united");
        model.addAttribute("table", tableInfos);
        return "testtable";
    }

    /* MATCHES */
    @RequestMapping("/testmatch")
    public String test2(Model model) {
        List<MatchInfo> matchInfos = matchInfoService.getMatchinfoByCountryName("united");
        model.addAttribute("match", matchInfos);
        return "testmatch";
    }

    /* SCORERS */
    @RequestMapping("/testscorer")
    public String test3(Model model) {
        List<ScorerInfo> scorerInfos = scorerInfoService.getScorerInfoByCountryName("united");
        model.addAttribute("scorer", scorerInfos);
        return "testscorer";
    }

    /* GOAL DIFFERENCE */
    @RequestMapping("/testgoaldiff")
    public String test4(Model model) {
        List<GoalDifferenceInfo> goalDifferenceInfos = goalDifferenceInfoService.getGoalDifferenceInfoByCountryName("united");
        model.addAttribute("goalDifference", goalDifferenceInfos);
        return "testgoaldiff";
    }
}
