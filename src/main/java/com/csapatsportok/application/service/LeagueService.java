package com.csapatsportok.application.service;

import com.csapatsportok.application.repository.GoalRepository;
import com.csapatsportok.application.repository.LeagueRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class LeagueService {

    private LeagueRepository leagueRepo;
    @Autowired
    public void setLeagueRepo(LeagueRepository leagueRepo) {

        this.leagueRepo = leagueRepo;
    }
}
