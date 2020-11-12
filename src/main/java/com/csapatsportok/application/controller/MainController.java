package com.csapatsportok.application.controller;

import com.csapatsportok.application.info.GoalDifferenceInfo;
import com.csapatsportok.application.info.MatchInfo;
import com.csapatsportok.application.info.ScorerInfo;
import com.csapatsportok.application.info.TableInfo;
import com.csapatsportok.application.domain.*;
import com.csapatsportok.application.repository.GoalDifferenceInfoRepository;
import com.csapatsportok.application.repository.MatchInfoRepository;
import com.csapatsportok.application.repository.ScorerInfoRepository;
import com.csapatsportok.application.repository.TableInfoRepository;
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


    private List<Country> getCountries() {
        return countryServ.getCountryRepo().findAll();
    }

    private int getMaxNumOfLeagues() {
        return countryServ.getCountryRepo().findMaxNumberOfLeagues();
    }

    private List<Team> getTeamsByLeague(League league) {
        return teamServ.getTeamRepo().findAllByLeague(league);
    }

    private League getLeagueByName(String name) {
        return leagueServ.getLeagueByName(name);
    }


    @RequestMapping("/countries")
    public String index(Model model) {
        // Countries + Their Leagues
        model.addAttribute("countries", getCountries());
        model.addAttribute("maxNumOfLeagues", getMaxNumOfLeagues());
        return "countries";
    }

    @RequestMapping("/league/{name}")
    public String league(@PathVariable(value = "name") String name, Model model) {
        League league = getLeagueByName(name);
        model.addAttribute("teams", getTeamsByLeague(league));
        return "leagues";
    }


    /* TABLE */
    private TableInfoRepository tableInfoRepo;
    @Autowired
    public void setTableInfoRepository(TableInfoRepository tableRepo) { this.tableInfoRepo = tableRepo; }

    @RequestMapping("/testtable")
    public String test1(Model model) {
        List<TableInfo> tableInfos = tableInfoRepo.getTableInfoByCountryName("united");
        model.addAttribute("table", tableInfos);
        return "testtable";
    }

    /* MATCHES */
    private MatchInfoRepository matchInfoRepo;
    @Autowired
    public void setMatchInfoRepo(MatchInfoRepository matchInfoRepo) { this.matchInfoRepo = matchInfoRepo; }

    @RequestMapping("/testmatch")
    public String test2(Model model) {
        List<MatchInfo> matchInfos = matchInfoRepo.getMatchinfoByCountryName("united");
        model.addAttribute("match", matchInfos);
        return "testmatch";
    }

    /* SCORERS */
    private ScorerInfoRepository scorerInfoRepo;
    @Autowired
    public void setScorerInfoRepo(ScorerInfoRepository scorerInfoRepo) { this.scorerInfoRepo = scorerInfoRepo; }

    @RequestMapping("/testscorer")
    public String test3(Model model) {
        List<ScorerInfo> scorerInfos = scorerInfoRepo.getScorerInfoByCountryName("united");
        model.addAttribute("scorer", scorerInfos);
        return "testscorer";
    }

    /* GOAL DIFFERENCE */
    private GoalDifferenceInfoRepository goalDifferenceInfoRepo;
    @Autowired
    public void setGoalDifferenceInfoRepo(GoalDifferenceInfoRepository goalDifferenceInfoRepo) { this.goalDifferenceInfoRepo = goalDifferenceInfoRepo; }

    @RequestMapping("/testgoaldiff")
    public String test4(Model model) {
        List<GoalDifferenceInfo> goalDifferenceInfos = goalDifferenceInfoRepo.getGoalDifferenceInfoByCountryName("united");
        model.addAttribute("goalDifference", goalDifferenceInfos);
        return "testgoaldiff";
    }


//    private HashMap<Team, List<Player>> getRosters() {
//        List<Team> teams = teamServ.getTeamRepo().findAll();
//        HashMap<Team, List<Player>> rosters = new HashMap<Team, List<Player>>();
//        for (Team team : teams) {
//            List<Player> players = playerServ.getPlayerRepo().findAllByTeam(team);
//            rosters.put(team, players);
//        }
//        return rosters;
//    }

//    @GetMapping("/all")
//    public ResponseEntity<?> getTeamPlayerInfo() {
//        return ResponseEntity.ok(getRosters());
//    }
//
//    @RequestMapping("/postgoal")
//    public ResponseEntity<Object> addGoal() {
//        Goal goal = new Goal();
//        goalServ.getGoalRepo().save(goal);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

//    @RequestMapping("/postgoals")
//    public ResponseEntity<Object> addGoals() {
//        Random rnd = new Random();
//        var games = gameServ.getGameRepo().findAll();
//        var rosters = getRosters();
//        for (Game game : games) {
//            for (int i = 0; i < game.getNumHomeGoals(); i++) {
//                int randomIdx = rnd.nextInt(rosters.get(game.getHomeTeam()).size());
//                Goal goal = new Goal();
//                goal.setGame(game);
//                goal.setTeam(game.getHomeTeam());
//                goal.setPlayer(rosters.get(game.getHomeTeam()).get(randomIdx));
//                goalServ.getGoalRepo().save(goal);
//            }
//            for (int i = 0; i < game.getNumAwayGoals(); i++) {
//                int randomIdx = rnd.nextInt(rosters.get(game.getAwayTeam()).size());
//                Goal goal = new Goal();
//                goal.setGame(game);
//                goal.setTeam(game.getAwayTeam());
//                goal.setPlayer(rosters.get(game.getAwayTeam()).get(randomIdx));
//                goalServ.getGoalRepo().save(goal);
//            }
//        }
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

}
