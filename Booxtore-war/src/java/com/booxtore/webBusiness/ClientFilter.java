/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author netbean
 */
public class ClientFilter implements Filter {

    private FilterConfig fc = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc = filterConfig;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        Auth user = (session != null) ? (Auth) session.getAttribute("auth") : null;
        if( user != null && user.isConnected() ) {
            chain.doFilter(request, response);
        } else {
            //default handling - do nothing and forward reqeust to filter chain
            HttpServletResponse res = (HttpServletResponse)response;
            res.sendRedirect(fc.getServletContext().getContextPath()+"/login.html");
        }
    }
    @Override
    public void destroy() {    }
}   

