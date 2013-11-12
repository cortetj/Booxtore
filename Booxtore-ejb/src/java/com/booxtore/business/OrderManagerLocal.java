/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Book;
import com.booxtore.entity.OrderRow;
import com.booxtore.entity.Orders;
import com.booxtore.entity.User;
import com.booxtore.model.Cart;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author netbean
 */
@Local
public interface OrderManagerLocal {

    void addOrder(User user, Cart shoppingCart,String credit_card);

    List<Orders> getOrdersByUser(User user);

    List<Orders> getOrdersByState(int state);

    Orders getOrderById(int id);

    void updateOrderById(int id, int state);

    float getTotalPriceOrder(int id);

}
