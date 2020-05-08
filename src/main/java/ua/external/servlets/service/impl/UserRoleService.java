package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.UserRoleDao;
import ua.external.servlets.entity.UserRole;
import ua.external.servlets.service.IUserRoleService;
import ua.external.servlets.service.ServiceException;

import java.util.Optional;

public class UserRoleService implements IUserRoleService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<UserRole> findUserRoleById(Long id) throws ServiceException {
        UserRoleDao userRoleDao = new UserRoleDao();
        EntityTransaction transaction = new EntityTransaction();
        Optional<UserRole> optionalUserRole;

        transaction.beginNoTransaction(userRoleDao);
        try {
            optionalUserRole = userRoleDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return optionalUserRole;
    }
}
