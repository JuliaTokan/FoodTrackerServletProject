package ua.external.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static ua.external.servlets.util.cоnst.JspConst.PARAM_PASSWORD;

/**
 * The {@code EncodingFilter} class
 * is an implementation of {@code Filter} interface.
 * Sets character encoding UTF-8 to each request and response objects.
 */
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
