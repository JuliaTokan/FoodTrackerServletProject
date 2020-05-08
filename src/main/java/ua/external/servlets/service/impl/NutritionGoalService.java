package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.NutritionGoalDao;
import ua.external.servlets.entity.NutritionGoal;
import ua.external.servlets.service.INutritionGoalService;
import ua.external.servlets.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class NutritionGoalService implements INutritionGoalService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<NutritionGoal> findNutritionGoalById(Long id) throws ServiceException {
        NutritionGoalDao nutritionGoalDao = new NutritionGoalDao();
        EntityTransaction transaction = new EntityTransaction();
        Optional<NutritionGoal> optional;

        transaction.beginNoTransaction(nutritionGoalDao);
        try {
            optional = nutritionGoalDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return optional;
    }

    @Override
    public List<NutritionGoal> findAllNutritionGoals() throws ServiceException {
        NutritionGoalDao nutritionGoalDao = new NutritionGoalDao();
        EntityTransaction transaction = new EntityTransaction();
        List<NutritionGoal> list;

        transaction.beginNoTransaction(nutritionGoalDao);
        try {
            list = nutritionGoalDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return list;
    }
}
