package ua.external.servlets.service;

import ua.external.servlets.entity.Meals;

import java.util.List;

public interface IMealsService {
    boolean createMeals(Meals meals) throws ServiceException;

    List<Meals> getAllMealForUser(Long userId) throws ServiceException;

    boolean deleteMealsById(Long id) throws ServiceException;
}
