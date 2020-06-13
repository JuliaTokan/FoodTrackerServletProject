package ua.external.servlets.command;

import ua.external.servlets.command.impl.*;
import ua.external.servlets.command.impl.admin.AdminDeleteProductCommand;
import ua.external.servlets.command.impl.admin.AdminEditProductCommand;
import ua.external.servlets.command.impl.admin.AdminProductCommand;
import ua.external.servlets.command.impl.client.ClientInfoPageCommand;
import ua.external.servlets.command.impl.client.ClientUpdateCommand;
import ua.external.servlets.command.impl.client.ClientUpdatePageCommand;
import ua.external.servlets.command.impl.client.CreateClientInfoCommand;
import ua.external.servlets.command.impl.info.AboutPageCommand;
import ua.external.servlets.command.impl.info.ContactsPageCommand;
import ua.external.servlets.command.impl.info.RecipesPageCommand;
import ua.external.servlets.command.impl.meals.AddMealsCommand;
import ua.external.servlets.command.impl.meals.DeleteMealsCommand;
import ua.external.servlets.command.impl.meals.MealsPageCommand;
import ua.external.servlets.command.impl.product.AddProductCommand;
import ua.external.servlets.command.impl.progress.ProgressPageCommand;

public enum CommandType {
    SIGNUPGET(new RegisterPageCommand()),

    SIGNUPPOST(new RegisterCommand()),

    SIGNINPOST(new LoginCommand()),

    SIGNOUTGET(new LogoutCommand()),

    CLIENTINFOGET(new ClientInfoPageCommand()),

    CLIENTINFOPOST(new CreateClientInfoCommand()),

    CLIENTEDITGET(new ClientUpdatePageCommand()),

    CLIENTEDITPOST(new ClientUpdateCommand()),

    USERMEALSGET(new MealsPageCommand()),

    USERMEALSPOST(new AddMealsCommand()),

    USERMEALSDELETEPOST(new DeleteMealsCommand()),

    USERPRODUCTPOST(new AddProductCommand()),

    ADMINPRODUCTDELETEPOST(new AdminDeleteProductCommand()),

    ADMINPRODUCTSGET(new AdminProductCommand()),

    ADMINPRODUCTEDITPOST(new AdminEditProductCommand()),

    LOCALESETGET(new LocaleCommand()),

    USERPROGRESSGET(new ProgressPageCommand()),

    INFOABOUTGET(new AboutPageCommand()),

    INFORECIPESGET(new RecipesPageCommand()),

    INFOCONTACTGET(new ContactsPageCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }

    public static ActionCommand getCurrentCommand(String action) {
        return CommandType.valueOf(action.toUpperCase()).command;
    }
}
