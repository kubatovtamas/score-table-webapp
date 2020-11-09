package com.csapatsportok.application.service;

import com.csapatsportok.application.repository.PlayerRepository;
import com.csapatsportok.application.repository.TeamRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class TeamService {

    private TeamRepository teamRepo;
    @Autowired
    public void setTeamRepo(TeamRepository teamRepo) {

        this.teamRepo = teamRepo;
    }
}
