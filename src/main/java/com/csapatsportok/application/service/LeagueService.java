package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.League;
import com.csapatsportok.application.repository.GoalRepository;
import com.csapatsportok.application.repository.LeagueRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class LeagueService {

    private LeagueRepository leagueRepo;
    @Autowired
    public void setLeagueRepo(LeagueRepository leagueRepo) {

        this.leagueRepo = leagueRepo;
    }

    public League getLeagueByName(String name) {
        return leagueRepo.findByName(name);
    }


    public List<League> getAllLeagues() {
        return leagueRepo.findAll();
    }
}
