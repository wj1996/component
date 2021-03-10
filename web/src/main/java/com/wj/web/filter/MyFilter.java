package com.wj.web.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String origin = req.getHeader("Origin");
        if (StringUtils.isNotBlank(origin)) {
            resp.setHeader("Access-Control-Allow-Origin",origin);
        }
        resp.setHeader("Access-Control-Allow-Headers","Content-Type,Access-Token");
        chain.doFilter(request,response);
    }
}
