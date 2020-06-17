package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.ProductDao;
import ua.external.servlets.dao.impl.UserDao;
import ua.external.servlets.entity.Meals;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.ServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements ua.external.servlets.service.IUserService {
    final static Logger logger = LogManager.getLogger();

    private MealsService mealsService = new MealsService();

    @Override
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();
        Optional<User> optionalUser;

        transaction.beginNoTransaction(userDao);
        try {
            optionalUser = userDao.findByLogin(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return optionalUser;
    }

    @Override
    public boolean createUser(User user) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag = false;

        transaction.beginNoTransaction(userDao);
        try {
            flag = userDao.create(user);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return flag;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        UserDao userDao = new UserDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag = false;

        transaction.beginNoTransaction(userDao);
        try {
            flag = userDao.update(user);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return flag;
    }

    @Override
    public Integer countCalories(User user) throws ServiceException {
        Integer sum = 0;
        List<Meals> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        sum = meals.stream().mapToInt(x -> x.getWeight()*x.getProduct().getCalories()/100).sum();
        return sum;
    }

    @Override
    public Integer countProtein(User user) throws ServiceException {
        Integer sum = 0;
        List<Meals> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        sum = meals.stream().mapToInt(x -> x.getWeight()*x.getProduct().getProtein().intValue()/100).sum();
        return sum;
    }

    @Override
    public Integer countFats(User user) throws ServiceException {
        Integer sum = 0;
        List<Meals> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        sum = meals.stream().mapToInt(x -> x.getWeight()*x.getProduct().getFats().intValue()/100).sum();
        return sum;
    }

    @Override
    public Integer countCarbohydrates(User user) throws ServiceException {
        Integer sum = 0;
        List<Meals> meals = mealsService.getAllMealForUserByDate(user.getId(), LocalDate.now());
        sum = meals.stream().mapToInt(x -> x.getWeight()*x.getProduct().getCarbohydrates().intValue()/100).sum();
        return sum;
    }
}
