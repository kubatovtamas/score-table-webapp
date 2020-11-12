package com.csapatsportok.application.service;

import com.csapatsportok.application.info.GoalDifferenceInfo;
import com.csapatsportok.application.repository.GoalDifferenceInfoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class GoalDifferenceInfoService {

    private GoalDifferenceInfoRepository goalDifferenceInfoRepo;

    @Autowired
    public void setGoalDifferenceInfoRepo(GoalDifferenceInfoRepository goalDifferenceInfoRepo) {
        this.goalDifferenceInfoRepo = goalDifferenceInfoRepo;
    }

    public List<GoalDifferenceInfo> getGoalDifferenceInfoByLeagueName(String name) {
        return goalDifferenceInfoRepo.getGoalDifferenceInfoByLeagueName(name);
    }
}
