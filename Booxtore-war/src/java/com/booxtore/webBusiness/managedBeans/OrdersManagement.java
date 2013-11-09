/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.OrderManagerLocal;
import com.booxtore.entity.Orders;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author netbean
 */
@ManagedBean
@RequestScoped
public class OrdersManagement {
    @EJB
    private OrderManagerLocal orderManager;

    /**
     * Creates a new instance of OrdersManagement
     */
    public OrdersManagement() {
    }
    
    
    public List<Orders> listOrdersByState( short state ) {
        return orderManager.getOrdersByState(state);
    }
}
