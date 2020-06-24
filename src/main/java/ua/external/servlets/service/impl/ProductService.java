package ua.external.servlets.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.EntityTransaction;
import ua.external.servlets.dao.impl.ProductDao;
import ua.external.servlets.entity.Product;
import ua.external.servlets.service.IProductService;
import ua.external.servlets.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {
    final static Logger logger = LogManager.getLogger();

    @Override
    public boolean createProduct(Product product) throws ServiceException {
        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean flag = false;

        transaction.beginNoTransaction(productDao);
        try {
            flag = productDao.create(product);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return flag;
    }

    @Override
    public List<Product> findAllProductsForUser(Long userId) throws ServiceException {
        List<Product> products;

        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        transaction.beginNoTransaction(productDao);
        try {
            products = productDao.findAllForUser(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return products;
    }

    @Override
    public Optional<Product> findProductById(Long id) throws ServiceException {
        Optional<Product> optionalProduct;

        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        transaction.beginNoTransaction(productDao);
        try {
            optionalProduct = productDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return optionalProduct;
    }

    @Override
    public boolean updateProduct(Product product) throws ServiceException {
        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean updated = false;

        transaction.beginNoTransaction(productDao);
        try {
            updated = productDao.update(product);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return updated;
    }

    @Override
    public boolean deleteProductById(Long id) throws ServiceException {
        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        boolean deleted = false;

        transaction.beginNoTransaction(productDao);
        try {
            deleted = productDao.deleteById(id);

        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return deleted;
    }


    @Override
    public List<Product> findAllProductsForUserByName(Long userId, String name) throws ServiceException {
        List<Product> products;

        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        transaction.beginNoTransaction(productDao);
        try {
            products = productDao.findAllForUserByName(userId, name);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return products;
    }

    @Override
    public int getNumberOfRows(Long userId) throws ServiceException {
        int num = 0;
        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        transaction.beginNoTransaction(productDao);
        try {
            num = productDao.getNumberOfRows(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return num;
    }

    @Override
    public List<Product> findAllProductsForUser(Long userId, int currentPage, int recordsPerPage) throws ServiceException {
        List<Product> products;

        ProductDao productDao = new ProductDao();
        EntityTransaction transaction = new EntityTransaction();

        transaction.beginNoTransaction(productDao);
        try {
            products = productDao.findAllByPageForUser(userId, currentPage, recordsPerPage);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Exception while executing service", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }

        return products;
    }
}
