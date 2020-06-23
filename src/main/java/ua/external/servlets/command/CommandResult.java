package ua.external.servlets.command;

/**
 * The {@code CommandResult} class
 * contains two fields -
 * page and isRedirect,
 * that are used with a controller to find out where and how
 * a request and response should be processed after the controller.
 */
public class CommandResult {
    private String page;
    private boolean isRedirect;

    public CommandResult() {
    }

    public CommandResult(String page) {
        this.page = page;
        isRedirect = false;
    }

    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
