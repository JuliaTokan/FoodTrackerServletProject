package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.MealsDao;
import ua.external.servlets.dao.impl.ProductDao;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.service.IMealsService;
import ua.external.servlets.service.ServiceException;

import java.util.List;

public class MealsService implements IMealsService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public boolean createMeals(Meals meals) throws ServiceException {
        MealsDao mealsDao = new MealsDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag;

        transaction.beginNoTransaction(mealsDao);
        try {
            flag = mealsDao.create(meals);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return flag;
    }

    @Override
    public List<Meals> getAllMealForUser(Long userId) throws ServiceException {
        List<Meals> meals;
        MealsDao mealsDao = new MealsDao();
        EntityTransaction transaction = new EntityTransaction();

        transaction.beginNoTransaction(mealsDao);
        try {
            meals = mealsDao.findAllByUser(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return meals;
    }

    @Override
    public boolean deleteMealsById(Long id) throws ServiceException {
        MealsDao mealsDao = new MealsDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag;

        transaction.beginNoTransaction(mealsDao);
        try {
            flag = mealsDao.delete(id);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return flag;
    }
}
