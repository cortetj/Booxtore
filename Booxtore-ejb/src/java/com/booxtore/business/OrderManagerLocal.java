/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Book;
import com.booxtore.entity.Orders;
import com.booxtore.entity.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author netbean
 */
@Local
public interface OrderManagerLocal {

    List<Orders> getListOrder(User user);

    void addOrder(Date date, short state, Date date_state, String credit_card, User user);

    void addOrderRows(int quantity, Book book, Orders order);
    
}
