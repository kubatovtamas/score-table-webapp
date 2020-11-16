package com.csapatsportok.application.service;

import com.csapatsportok.application.domain.Game;
import com.csapatsportok.application.domain.Goal;
import com.csapatsportok.application.repository.GameRepository;
import com.csapatsportok.application.repository.GoalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class GoalService {

    private GoalRepository goalRepo;
    @Autowired
    public void setGoalRepo(GoalRepository goalRepo) {

        this.goalRepo = goalRepo;
    }

    public List<Goal> getAllGoals() {
        List<Goal> goals = (List<Goal>) goalRepo.findAll();

        if (goals.size() > 0) {
            return goals;
        } else {
            return new ArrayList<Goal>();
        }
    }

    public Goal getGoalById(Long id) throws RuntimeException {
        Optional<Goal> goal = goalRepo.findById(id);

        if (goal.isPresent()) {
            return goal.get();
        } else {
            throw new RuntimeException("Goal with id: " + id + " not found");
        }
    }

    public void createOrUpdateGoal(Goal entity) {
        if (entity.getId() == null) {
            /* Save New Entity */
            entity = goalRepo.save(entity);
        } else {
            /* Edit Existing Entity */
            Optional<Goal> goal = goalRepo.findById(entity.getId());

            if (goal.isPresent()) {
                Goal newEntity = goal.get();
                newEntity.setPlayer(entity.getPlayer());
                newEntity.setTeam(entity.getTeam());
                newEntity.setGame(entity.getGame());

                newEntity = goalRepo.save(newEntity);
            } else {
                entity = goalRepo.save(entity);
            }
        }
    }

    public void deleteGoalById(Long id) throws RuntimeException {
        Optional<Goal> goal = goalRepo.findById(id);

        if(goal.isPresent()) {
            goalRepo.deleteById(id);
        } else {
            throw new RuntimeException("Goal with id: " + id + " not found");
        }
    }
}
