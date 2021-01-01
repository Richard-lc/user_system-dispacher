package com.qf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "IsLoginFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "ignoreJsp", value = "index.jsp,login.jsp"),
        @WebInitParam(name = "ignoreAction", value = "login,logout,CodeServlet")
})
public class IsLoginFilter implements Filter {

    private List<String> ignoreJsp;
    private List<String> ignoreAction;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Object admin = request.getSession().getAttribute("admin");

        if (admin != null || this.isIgnore(request)) {
            chain.doFilter(request, response);
        } else {
            response.getWriter().write("你还没有登录，请先<a href='/login.jsp'>登录</a>！");
        }
    }

    private boolean isIgnore(HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        String action = requestURI.substring(requestURI.lastIndexOf("/") + 1);

//        if ("/user_system/".equalsIgnoreCase(requestURI)){
        if ("/".equalsIgnoreCase(requestURI)) {
            return true;
        }

        if (this.ignoreAction.contains(action)) {
            return true;
        }

//        if (ignoreJsp.contains(requestURI.substring(13, requestURI.length()))){
        if (this.ignoreJsp.contains(requestURI.substring(1, requestURI.length()))) {
            return true;
        }

        return false;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String[] ignoreJsps = config.getInitParameter("ignoreJsp").split(",");
        this.ignoreJsp = Arrays.asList(ignoreJsps);
        String[] ignoreActions = config.getInitParameter("ignoreAction").split(",");
        this.ignoreAction = Arrays.asList(ignoreActions);
    }

}
