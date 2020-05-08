package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.GenderDao;
import ua.external.servlets.entity.Gender;
import ua.external.servlets.service.IGenderService;
import ua.external.servlets.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class GenderService implements IGenderService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Gender> findGenderById(Long id) throws ServiceException {
        GenderDao genderDao = new GenderDao();
        EntityTransaction transaction = new EntityTransaction();
        Optional<Gender> optional;

        transaction.beginNoTransaction(genderDao);
        try {
            optional = genderDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return optional;
    }

    @Override
    public List<Gender> findAllGenders() throws ServiceException {
        GenderDao genderDao = new GenderDao();
        EntityTransaction transaction = new EntityTransaction();
        List<Gender> list;

        transaction.beginNoTransaction(genderDao);
        try {
            list = genderDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return list;
    }
}
