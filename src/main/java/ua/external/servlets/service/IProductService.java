package ua.external.servlets.service;

import ua.external.servlets.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * The interface implements behavior for ProductService.
 */
public interface IProductService {
    boolean createProduct(Product product) throws ServiceException;

    List<Product> findAllProductsForUser(Long userId) throws ServiceException;

    Optional<Product> findProductById(Long id) throws ServiceException;

    boolean updateProduct(Product product) throws ServiceException;

    boolean deleteProductById(Long id) throws ServiceException;

    List<Product> findAllProductsForUserByName(Long userId, String name) throws ServiceException;

    List<Product> findAllProductsForUser(Long userId, int currentPage, int recordsPerPage) throws ServiceException;

    int getNumberOfRows(Long userId) throws ServiceException;
}
