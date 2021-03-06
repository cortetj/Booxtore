/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.AccountManagerLocal;
import com.booxtore.business.OrderManagerLocal;
import com.booxtore.entity.OrderRow;
import com.booxtore.entity.Orders;
import com.booxtore.entity.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author netbean
 */
@ManagedBean
@RequestScoped
public class OrdersManagement {
    @EJB
    private OrderManagerLocal orderManager;

    
    private int id;
    private short state;
    private Date date;
    private Date dateState;
    private List<OrderRow> rowCollection;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateState() {
        return dateState;
    }

    public void setDateState(Date dateState) {
        this.dateState = dateState;
    }

    public List<OrderRow> getRowCollection() {
        return rowCollection;
    }

    public void setRowCollection(List<OrderRow> rowCollection) {
        this.rowCollection = rowCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Creates a new instance of OrdersManagement
     */
    public OrdersManagement() {
    }
    
    /**
     * Liste les commandes selon l'état choisi
     * @param state l'état souhaité
     * @return La liste des commandes 
     */
    public List<Orders> listOrdersByState( short state ) {
        return orderManager.getOrdersByState(state);
    }
    
    /**
     * Récupère une commande selon un id envoyé
     * @param id id de la commande que l'on veut récupérer
     * @return la commande trouvée selon l'id envoyé
     */
    public Orders getOrderById(int id){
        return orderManager.getOrderById(id);
    }
    
    /**
     * Récupère le prix total d'une commande selon un id
     * @param id id de la commande
     * @return le prix total de la commande
     */
    public float getTotalPriceOrder(int id){
        return orderManager.getTotalPriceOrder(id);
    }
    
    
    /**
     * Charge les données relatives à la commande à afficher. 
     * @param idOrder 
     */
    public void loadOrder(int idOrder){
        Orders o = null;
        if(idOrder > 0) o = orderManager.getOrderById(idOrder);
        else{
            id = 0;
        }
        
        if(o != null){
            id = idOrder;
            state = o.getOrderState();
            date = o.getOrderDate();
            dateState = o.getOrderDateState();
            user = o.getUserUserId();
            rowCollection = (List<OrderRow>) o.getOrderRowCollection();
        }
    }
    
    /**
     * Mise à jour de l'état de la commande chargé sur la page
     */    
    public void updateOrderState(){
        ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
        
        orderManager.updateOrderById(id, state);
        
        try {
            context.redirect(context.getRequestContextPath()+"/admin_area/order.html?id="+id);
        }catch (IOException e) {
            System.err.println("Erreur update état commande");
        }
    }
}
