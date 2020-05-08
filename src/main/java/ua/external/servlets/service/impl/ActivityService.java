package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.ActivityDao;
import ua.external.servlets.entity.Activity;
import ua.external.servlets.service.IActivityService;
import ua.external.servlets.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class ActivityService implements IActivityService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Activity> findActivityById(Long id) throws ServiceException {
        ActivityDao activityDao = new ActivityDao();
        EntityTransaction transaction = new EntityTransaction();
        Optional<Activity> optional;

        transaction.beginNoTransaction(activityDao);
        try {
            optional = activityDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return optional;
    }

    @Override
    public List<Activity> findAllActivities() throws ServiceException {
        ActivityDao activityDao = new ActivityDao();
        EntityTransaction transaction = new EntityTransaction();
        List<Activity> list;

        transaction.beginNoTransaction(activityDao);
        try {
            list = activityDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return list;
    }
}
