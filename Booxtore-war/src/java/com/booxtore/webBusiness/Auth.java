/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness;

import com.booxtore.business.AccountManagerLocal;
import com.booxtore.entity.User;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author netbean
 */
@ManagedBean
@SessionScoped
public class Auth implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;
    
    @EJB
    private AccountManagerLocal accountManager;
    /**
     * Creates a new instance of Auth
     */
    
    private User user;
    
    private String username;
    private String password;
    private String error;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    public Auth() {
        user = null;
    }
    
    public User getUser(){
        return user;
    }
    
    public String  login() {
        user = null;
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            if( accountManager.isUser(username, sb.toString()) ) {
                this.user = accountManager.getUserByLogin(username);
                // Password est inutile, donc supprimé
                this.user.setUserPassword(null);
                password = null;
                //TODO: link vers page compte / un message d'accueil / etc.
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("user", user);
                return "login";
            }
            ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect(context.getRequestContextPath()+"/login.html?failed=true");
            } catch (IOException ex) {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/login?failed=true";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        return "login";
    }
    
    public boolean isConnected(){
        return (user != null);
    }
    
    public boolean isAdministrator() {
        if( isConnected() ) {
            return true;
        }
        return  false;
    }
    
    public void checkError() {
        error = null;
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // Erreur de login?
        if("true".equals((String)request.getParameter("failed"))) {
           error = "Login / Mot de passe incorrecte!";
        }
        // Erreur de timed out session?
        else if(request.getRequestedSessionId() !=  null && !request.isRequestedSessionIdValid()) {
            error = "Session expirée !";
        }
    }
}
