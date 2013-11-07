/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.User;
import javax.ejb.Local;

/**
 *
 * @author netbean
 */
@Local
public interface AccountManagerLocal {

    User getUserByLogin(String login);

    boolean isUser(String name, String password);

    User createUser(String userFirstName, String userLastName, String userMail, String userAddress, String userCity, String userCityNumber, String userLogin, String userPassword);
    
}
