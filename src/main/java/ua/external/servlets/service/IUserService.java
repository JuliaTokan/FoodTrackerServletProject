package ua.external.servlets.service;

import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface implements behavior for UserService.
 */
public interface IUserService {
    Optional<User> findUserByLogin(String login) throws ServiceException;

    boolean createUser(User user) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    Integer countCalories(User user) throws ServiceException;

    Integer countProtein(User user) throws ServiceException;

    Integer countFats(User user) throws ServiceException;

    Integer countCarbohydrates(User user) throws ServiceException;
}
