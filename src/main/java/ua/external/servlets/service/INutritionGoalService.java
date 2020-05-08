package ua.external.servlets.service;

import ua.external.servlets.entity.NutritionGoal;

import java.util.List;
import java.util.Optional;

public interface INutritionGoalService {
    Optional<NutritionGoal> findNutritionGoalById(Long id) throws ServiceException;

    List<NutritionGoal> findAllNutritionGoals() throws ServiceException;
}
