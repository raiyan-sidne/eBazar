package net.therap.ebazar.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hasin.raiyan
 * @since 3/23/21
 */
@WebFilter
public class AuthenticationFilter implements Filter {

    private List<String> defaultUrl;

    @Override
    public void init(FilterConfig filterConfig) {
        defaultUrl = new ArrayList<>();
        defaultUrl.add("/");
        defaultUrl.add("/login");
        defaultUrl.add("/register");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String action = httpServletRequest.getServletPath();
        HttpSession session = httpServletRequest.getSession(false);
        cleanCache(httpServletResponse);

        if (action.contains(".")) {
            chain.doFilter(request, response);
        } else if (defaultUrl.contains(action)) {
            if (Objects.nonNull(session) && verifyLoggedIn(session)) {
                httpServletResponse.sendRedirect("home");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (verifyLoggedIn(session)) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect("login");
            }
        }
    }

    private void cleanCache(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
    }

    private boolean verifyLoggedIn(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        return Objects.nonNull(userId);
    }
}