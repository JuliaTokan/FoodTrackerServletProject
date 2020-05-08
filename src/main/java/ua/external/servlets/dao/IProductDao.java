package ua.external.servlets.dao;

import ua.external.servlets.entity.Product;

import java.util.List;

public interface IProductDao{
    List<Product> findAllForUser(Long id) throws DaoException;
    boolean deleteById(Long id) throws DaoException;
    List<Product> findUsersProducts(Long id) throws DaoException;
    List<Product> findAllForUserByName(Long id, String name) throws DaoException;
}
