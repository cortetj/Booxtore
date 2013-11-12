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
    
    /**
     *  Recherche et récupère un utilisateur selon son login
     * 
     * @param login login utilisé par l'utilisateur
     * @return retourne l'objet utilisateur
     */
    @Override
    public User getUserByLogin(String login) {
        try {
            return em.createNamedQuery("User.findByUserLogin", User.class).setParameter("userLogin", login).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    /**
     * Permet de savoir si le combo login / mot de passe correspond à un utilisateur 
     * 
     * @param login login de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return True si les informations d'authentifications sont correctes, sinon renvoie False
     */
    @Override
    public boolean isUser(String login, String password) {
        User usr = null;
        try{
            usr = em.createQuery("SELECT u FROM User u WHERE (u.userLogin = :userLogin OR u.userMail = :userMail) AND u.userPassword = :userPassword", User.class)
                         .setParameter("userLogin", login)
                         .setParameter("userMail", login)
                         .setParameter("userPassword", password)
                         .getSingleResult();
        } catch(Exception e) {
            return false;
        }
        // Utilisateur existant - renvoyer True
        if( usr != null ) {
            return true;
        }
        // Utilisateur inexistant - renvoyer False
        return false;
    }

    /**
     * Permet la création d'un nouvel utilisateur
     * 
     * @param userFirstName Prénom de l'utilisateur 
     * @param userLastName Nom de famille de l'utilisateur 
     * @param userMail Email de l'utilisateur 
     * @param userAddress Adresse de l'utilisateur 
     * @param userCity Ville de l'utilisateur 
     * @param userCityNumber Code postal de l'utilisateur 
     * @param userLogin Login de l'utilisateur 
     * @param userPassword Mot de passe de l'utilisateur 
     * @return renvoie l'utilisateur créé de l'utilisateur 
     */
    @Override
    public User createUser(String userFirstName, String userLastName, String userMail, String userAddress, String userCity, String userCityNumber, String userLogin, String userPassword) {
            User user = null;
           // login déjà pris?
        if( getUserByLogin(userLogin) == null) {
            // non: créer l'utilisateur
            
            user = new User(null, userFirstName, userLastName, userMail, userAddress, userCity, userCityNumber, userLogin, userPassword, (short)0);
            em.persist(user);
         }
        // Renvoie l'utilisateur (! ou null si login déjà pris !)
        return user;
    }
    
    /**
     * Permet la mise à jour des informations d'un utilisateur ciblé par son ID
     * 
     * @param userId ID de l'utilisateur ciblé
     * @param userFirstName Prénom de l'utilisateur 
     * @param userLastName Nom de famille de l'utilisateur 
     * @param userMail Email de l'utilisateur 
     * @param userAddress Adresse de l'utilisateur 
     * @param userCity Ville de l'utilisateur 
     * @param userCityNumber Code postal de l'utilisateur 
     * @param userLogin Login de l'utilisateur 
     * @param userPassword Mot de passe de l'utilisateur 
     */
    @Override
    public void updateUser(int userId, String userFirstName, String userLastName, String userMail, String userAddress, String userCity, String userCityNumber, String userLogin, String userPassword){
        User u = null;
        u = em.createNamedQuery("User.findByUserId", User.class).setParameter("userId", userId).getSingleResult();
        // Si utilisateur non null
        if (u != null) {
            u.setUserFirstname(userFirstName);
            u.setUserLastname(userLastName);
            u.setUserMail(userMail);
            u.setUserAddress(userAddress);
            u.setUserCity(userCity);
            u.setUserCityNumber(userCityNumber);
            // Si le nouveau login est disponible
            if( getUserByLogin(userLogin) == null) {
                u.setUserLogin(userLogin);
            }
            u.setUserPassword(userPassword);

            em.merge(u);
        }
    }   
    
}
