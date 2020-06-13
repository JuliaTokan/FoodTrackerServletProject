package ua.external.servlets.command.impl.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.service.impl.ProductService;
import ua.external.servlets.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.external.servlets.util.cоnst.JspConst.*;

/**
 * Remove product by id.
 * Returns router to the same page.
 */
public class AdminDeleteProductCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminDeleteProductCommand.class);
    private ProductService productService = new ProductService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        Long id = Long.parseLong(request.getParameter(PARAM_ID));
        HttpSession session = request.getSession();
        try {
            if(productService.deleteProductById(id)){
                log.info("delete product with id="+id);
                page = request.getHeader(CURRENT_PAGE);
            }
            else {
                session.setAttribute(MODAL_EDIT, true);
                session.setAttribute(WRONG_DATA, true);
                return new CommandResult(request.getHeader(CURRENT_PAGE), true);
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = request.getHeader(CURRENT_PAGE);
        }
        return new CommandResult(page, true);
    }
}
