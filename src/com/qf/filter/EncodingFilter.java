package com.qf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*",initParams = {@WebInitParam(name = "charset",value = "utf8")})
public class EncodingFilter implements Filter {

    private String charset;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset="+ charset);

        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
    }

}
