package ua.external.servlets.command.impl.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.dao.DaoException;
import ua.external.servlets.dao.impl.ProductDao;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.util.cоnst.JspConst;
import ua.external.servlets.util.page.Page;
import ua.external.servlets.validator.DataValidator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static ua.external.servlets.util.cоnst.JspConst.*;

/**
 * Gets product information.
 * Validates this values, if input data is not valid,
 * returns router to the same page with message about invalid values.
 * Otherwise, create product and returns router to the same page.
 */
public class AdminProductCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminProductCommand.class);
    private ProductService productService = new ProductService();
    private static final int TOTAL_PER_PAGE = 10;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(PARAM_USER);

        List<Product> products = new ArrayList<>();

        final int TOTAL_PAGES;
        int numOfProducts = 0;
        try {
            numOfProducts = productService.getNumberOfRows(user.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (numOfProducts % TOTAL_PER_PAGE == 0) {
            TOTAL_PAGES = numOfProducts / TOTAL_PER_PAGE;
        } else {
            TOTAL_PAGES = numOfProducts / TOTAL_PER_PAGE + 1;
        }

        Integer page = getPage(request, TOTAL_PAGES);

        String name = request.getParameter(PARAM_NAME);
        if (name != null && !name.equals("")) {
            try {
                products = productService.findAllProductsForUserByName(user.getId(), name);
            } catch (ServiceException e) {
                log.error("Problem with service occurred!", e);
                return new CommandResult(request.getHeader(CURRENT_PAGE), true);
            }
        } else {
            try {
                products = productService.findAllProductsForUser(user.getId(), page, TOTAL_PER_PAGE);
            } catch (ServiceException e) {
                log.error("Problem with service occurred!", e);
                return new CommandResult(request.getHeader(CURRENT_PAGE), true);
            }
        }

        request.setAttribute(PARAM_PRODUCTS, products);
        request.setAttribute(PARAM_PAGES, TOTAL_PAGES);
        return new CommandResult(Page.ADMIN_PRODUCT_PAGE);
    }

    private Integer getPage(HttpServletRequest request, int TOTAL_PAGES) {
        String pageNumberString = request.getParameter(JspConst.PAGE_NUMBER);

        pageNumberString = pageNumberString == null ? "1" : pageNumberString;
        Integer page = 1;
        try {
            page = Integer.parseInt(pageNumberString);
        } catch (NumberFormatException e) {
            log.info("invalid page number format was received:" + pageNumberString);
        }

        if (page < 1 || page > TOTAL_PAGES) {
            log.info("invalid page number format was received:" + pageNumberString);
            page = 1;
        }

        return page;
    }
}
