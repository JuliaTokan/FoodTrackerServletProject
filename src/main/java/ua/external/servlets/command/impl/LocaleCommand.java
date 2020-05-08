package ua.external.servlets.command.impl;

import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;
import ua.external.servlets.util.cоnst.JspConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.external.servlets.util.cоnst.SessionConst.SESSION_LOCALE;

public class LocaleCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter(JspConst.LANGUAGE);
        request.getSession().setAttribute(SESSION_LOCALE, lang);
        return new CommandResult(request.getHeader(JspConst.CURRENT_PAGE), true);
    }
}
