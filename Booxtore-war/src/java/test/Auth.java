/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import com.booxtore.business.AccountManagerLocal;
import com.booxtore.entity.User;
import java.security.Principal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author netbean
 */
@ManagedBean
@SessionScoped
public class Auth {
    @EJB
    private AccountManagerLocal accountManager;

    /**
     * Creates a new instance of Auth
     */
    
    private User user;
    
    
    
    public Auth() {
    }
    
    public User getUser(){
        if(isConnected()){
            return user;
        }
        return null;
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        System.out.println(principal.getName());
        System.out.println("logout");
        return "login?faces-redirection=true";
    }
    
    public boolean isConnected(){
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        System.out.println(principal.getName());
        System.out.println("logout");
        if(user == null){
            if(principal != null){
                //TODO:getuserByLogin
                user = null;
                return true;
            }
        }
        return false;
    }
}
