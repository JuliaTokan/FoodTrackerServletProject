package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.ClientDao;
import ua.external.servlets.dao.impl.UserDao;
import ua.external.servlets.entity.*;
import ua.external.servlets.service.IClientService;
import ua.external.servlets.service.ServiceException;

import java.util.Optional;

public class ClientService implements IClientService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public boolean createClient(Client client) throws ServiceException {
        ClientDao clientDao = new ClientDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag = false;

        transaction.beginNoTransaction(clientDao);
        try {
            flag = clientDao.create(client);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return flag;
    }

    //@Override
    public boolean createClient(Client client, User user) throws ServiceException {
        ClientDao clientDao = new ClientDao();
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag = false;

        transaction.begin(clientDao, userDao);
        try {
            flag = clientDao.create(client);
            user.setClientId(client.getId());
            flag = userDao.update(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }

        return flag;
    }

    @Override
    public boolean updateClient(Client client) throws ServiceException {
        ClientDao clientDao = new ClientDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag = false;

        transaction.beginNoTransaction(clientDao);
        try {
            flag = clientDao.update(client);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return flag;
    }

    @Override
    public Optional<Client> findClientById(Long id) throws ServiceException {
        ClientDao clientDao = new ClientDao();
        EntityTransaction transaction = new EntityTransaction();
        Optional<Client> optionalClient;

        transaction.beginNoTransaction(clientDao);
        try {
            optionalClient = clientDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return optionalClient;
    }
}
