package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.EatPeriodDao;
import ua.external.servlets.entity.EatPeriod;
import ua.external.servlets.service.IEatPeriodService;
import ua.external.servlets.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class EatPeriodService implements IEatPeriodService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<EatPeriod> findEatPeriodById(Long id) throws ServiceException {
        EatPeriodDao eatPeriodDao = new EatPeriodDao();
        EntityTransaction transaction = new EntityTransaction();
        Optional<EatPeriod> optional;

        transaction.beginNoTransaction(eatPeriodDao);
        try {
            optional = eatPeriodDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return optional;
    }

    @Override
    public List<EatPeriod> findAllEatPeriods() throws ServiceException {
        EatPeriodDao eatPeriodDao = new EatPeriodDao();
        EntityTransaction transaction = new EntityTransaction();
        List<EatPeriod> list;

        transaction.beginNoTransaction(eatPeriodDao);
        try {
            list = eatPeriodDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return list;
    }
}
