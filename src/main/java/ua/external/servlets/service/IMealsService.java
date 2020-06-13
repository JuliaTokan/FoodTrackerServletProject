package ua.external.servlets.service;

import ua.external.servlets.entity.Meals;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface implements behavior for MealsService.
 */
public interface IMealsService {
    boolean createMeals(Meals meals) throws ServiceException;

    List<Meals> getAllMealForUser(Long userId) throws ServiceException;

    boolean deleteMealsById(Long id) throws ServiceException;

    List<Meals> getAllMealForUserByDate(Long userId, LocalDate date) throws ServiceException;
}
