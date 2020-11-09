package com.csapatsportok.application.service;

import com.csapatsportok.application.repository.GameRepository;
import com.csapatsportok.application.repository.GoalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class GoalService {

    private GoalRepository goalRepo;
    @Autowired
    public void setGoalRepo(GoalRepository goalRepo) {

        this.goalRepo = goalRepo;
    }
}
