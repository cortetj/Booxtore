/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author netbean
 */
@WebFilter(urlPatterns={"/admin_area/*"})
public class LibraireFilter implements Filter {

    private FilterConfig filterConfig = null;
    private boolean isConnected = false;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        FacesContext context = FacesContext.getCurrentInstance();
        Object usr = context.getExternalContext().getSessionMap().get("user");
        isConnected = (usr != null); 
        
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if(isConnected) {
            if(request instanceof HttpServletRequest) {
                    HttpServletRequest httpReq = (HttpServletRequest) request;
                    String uri = httpReq.getRequestURI();
                    // vérification des bonnes urls
                    if(uri != null && uri.equals("/admin_area/test2.html")) {
                            RequestDispatcher rd = filterConfig.
                                      getServletContext().
                                      getRequestDispatcher("/admin_area/test2.html");
                            if(rd != null) {
                                    rd.forward(request, response);
                                    return;
                            }
                    }

            }
        }
	//default handling - do nothing and forward reqeust to filter chain
        HttpServletResponse res = (HttpServletResponse)response;
        res.sendRedirect("/faces/test.html");
    }
    
    public void destroy() {    }

    @Override
    public boolean isLoggable(LogRecord lr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}   

