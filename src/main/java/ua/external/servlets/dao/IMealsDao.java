package ua.external.servlets.dao;

import ua.external.servlets.entity.Meals;

import java.util.List;

/**
 * Interface that implements additional behavior for MealsDao
 */
public interface IMealsDao {
    List<Meals> findAllByUser(Long id) throws DaoException;
}
