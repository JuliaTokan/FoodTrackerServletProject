package ua.external.servlets.command.impl.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.builder.ProductBuilder;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.entity.Product;
import ua.external.servlets.entity.User;
import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;
import ua.external.servlets.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.external.servlets.util.cоnst.JspConst.*;

public class AddProductCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddProductCommand.class);
    private ProductService productService = new ProductService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        String name = request.getParameter(PARAM_NAME);
        if (name == null || !DataValidator.validateName(name)) {
            log.info("invalid product name format was received:" + name);
            session.setAttribute(MODAL, true);
            session.setAttribute(INVALID_NAME, true);
            return new CommandResult(request.getHeader("referer"), true);
        }

        Integer calories = Integer.parseInt(request.getParameter(PARAM_CALORIES));
        if (calories == null || !DataValidator.validateCalories(calories)) {
            log.info("invalid calories format was received:" + calories);
            session.setAttribute(MODAL, true);
            session.setAttribute(INVALID_CALORIES, true);
            return new CommandResult(request.getHeader("referer"), true);
        }

        Double protein = Double.parseDouble(request.getParameter(PARAM_PROTEIN));
        if (protein == null || !DataValidator.validateNutrition(protein)) {
            log.info("invalid protein format was received:" + protein);
            session.setAttribute(MODAL, true);
            session.setAttribute(INVALID_PROTEIN, true);
            return new CommandResult(request.getHeader("referer"), true);
        }

        Double fats = Double.parseDouble(request.getParameter(PARAM_FATS));
        if (fats == null || !DataValidator.validateNutrition(fats)) {
            log.info("invalid fats format was received:" + fats);
            session.setAttribute(MODAL, true);
            session.setAttribute(INVALID_FATS, true);
            return new CommandResult(request.getHeader("referer"), true);
        }

        Double carbohydrates = Double.parseDouble(request.getParameter(PARAM_CARBOHYDRATES));
        if (carbohydrates == null || !DataValidator.validateNutrition(carbohydrates)) {
            log.info("invalid carbohydrates format was received:" + carbohydrates);
            session.setAttribute(MODAL, true);
            session.setAttribute(INVALID_CARBOHYDRATES, true);
            return new CommandResult(request.getHeader("referer"), true);
        }

        Product product;
        try{
            product = buildProduct(request);
            if(productService.createProduct(product)){
                log.info("create product with id="+product.getId());
                page = request.getHeader("referer");
            }
            else {
                session.setAttribute(MODAL, true);
                session.setAttribute(WRONG_DATA, true);
                return new CommandResult(request.getHeader("referer"), true);
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = request.getHeader(CURRENT_PAGE);
        }

        return new CommandResult(page, true);
    }

    private Product buildProduct(HttpServletRequest request) throws ServiceException {
        String name = request.getParameter(PARAM_NAME);
        Integer calories = Integer.parseInt(request.getParameter(PARAM_CALORIES));
        Double protein = Double.parseDouble(request.getParameter(PARAM_PROTEIN));
        Double fats = Double.parseDouble(request.getParameter(PARAM_FATS));
        Double carbohydrates = Double.parseDouble(request.getParameter(PARAM_CARBOHYDRATES));

        Boolean isPublic = request.getParameter(PARAM_PUBLIC)==null?false:request.getParameter(PARAM_PUBLIC).equals("on")?true:false;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(PARAM_USER);

        return new ProductBuilder()
                .setUserId(user.getId())
                .setName(name)
                .setCalories(calories)
                .setProtein(protein)
                .setFats(fats)
                .setCarbohydrates(carbohydrates)
                .setCommon(isPublic)
                .createProduct();
    }
}
