/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.AccountManagerLocal;
import com.booxtore.entity.User;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
    
    
    /**
     * Tente la connexion de l'utilisateur 
     * @return url de redirection
     */
    public String  login() {
        ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
        String url = "/index.html?failed=1";
        user = null;
        if( username.isEmpty() || username == null || password.isEmpty() || password == null ) {
            url = "/index.html?failed=0";
            try {
                context.redirect(context.getRequestContextPath()+url);
            } catch (IOException ex) {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
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
                password = null;
                //TODO: link vers page compte / un message d'accueil / etc.
                context.getSessionMap().put("user", user);
                if ( isAdministrator() != false) {
                    url = "/admin_area/";
                } else {
                    url = "?log=true";
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            context.redirect(context.getRequestContextPath()+url);
        } catch (IOException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Deconnecte la session en court
     * @return Redirige vers la page d'accueil
     */
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        return "index.html?faces-redirect=true";
    }
    
    /**
     * Vérifie si l'utilisateur est connecté
     * @return true si connecté, false si non connecté
     */
    public boolean isConnected(){
        return (user != null);
    }
    
    /**
     * Vérifie si la session en cours est une session administrateur
     * @return true si administrateur, false si non administrateur
     */
    public boolean isAdministrator() {
        if( isConnected() ) {
            return "libraire".equals(user.getUserGroupUserGroupId().getUserGroupName());
        }
        return  false;
    }
    
    /**
     * Affiche une erreur de connexion selon le paramètre passé dans l'url
     */
    public void checkError() {
        error = null;
        FacesContext context =  FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // Erreur de login?
        if("0".equals((String)request.getParameter("failed"))) {
           error = "Login / Mot de passe manquant !";
        }
        if("1".equals((String)request.getParameter("failed"))) {
           error = "Login / Mot de passe incorrecte !";
        }
        // Erreur de timed out session?
        else if(request.getRequestedSessionId() !=  null && !request.isRequestedSessionIdValid()) {
            error = "Session expirée !";
        }
    }
}
