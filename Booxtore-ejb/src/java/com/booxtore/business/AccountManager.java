/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Matthieu P.
 */
@Stateless
public class AccountManager implements AccountManagerLocal {
    @PersistenceContext(unitName = "Booxtore-ejbPU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public User getUserByLogin(String login) {
        return (User)em.createNamedQuery("User.findByUserLogin").setParameter("userLogin", login).getSingleResult();
    }

    @Override
    public boolean isUser(String login, String password) {
        try {
            User usr = (User) em.createQuery("SELECT u FROM User u WHERE (u.userLogin = :userLogin OR u.userMail = :userMail) AND u.userPassword = :userPassword")
                               .setParameter("userLogin", login)
                               .setParameter("userMail", login)
                               .setParameter("userPassword", password)
                               .getSingleResult();
            if( usr != null ) {
                return true;
            }
        } catch ( NoResultException noneFound) {
            return false;
        }
        return false;
    }

    @Override
    public User createUser(String userFirstName, String userLastName, String userMail, String userAddress, String userCity, String userCityNumber, String userLogin, String userPassword) {
         User user = new User();
         user.setUserFirstname(userFirstName);
         user.setUserLastname(userLastName);
         user.setUserMail(userMail);
         user.setUserAddress(userAddress);
         user.setUserCity(userCity);
         user.setUserCityNumber(userCityNumber);
         user.setUserLogin(userLogin);
         user.setUserPassword(userPassword);
         
         em.persist(user);
        
        return user;
    }
    
    
    
}
