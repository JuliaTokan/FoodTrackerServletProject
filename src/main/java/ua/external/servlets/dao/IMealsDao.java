package ua.external.servlets.dao;

import ua.external.servlets.entity.Meals;

import java.util.List;

public interface IMealsDao {
    List<Meals> findAllByUser(Long id) throws DaoException;
}
