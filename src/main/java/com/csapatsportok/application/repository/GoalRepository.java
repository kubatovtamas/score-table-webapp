package com.csapatsportok.application.repository;

import com.csapatsportok.application.domain.Goal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends CrudRepository<Goal, Long> {
}
