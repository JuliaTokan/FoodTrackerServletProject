package ua.external.servlets.command.impl;

import ua.external.servlets.command.ActionCommand;
import ua.external.servlets.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.external.servlets.util.page.Page.WELCOME_PAGE;

public class EmptyCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandResult(WELCOME_PAGE, true);
    }
}
