/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.OrderRow;
import com.booxtore.entity.Orders;
import com.booxtore.entity.User;
import com.booxtore.model.Cart;
import com.booxtore.model.CartItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Matthieu P.
 */
@Stateless
public class OrderManager implements OrderManagerLocal {
    @PersistenceContext(unitName = "Booxtore-ejbPU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

     /**
     * Ajoute une commande selon un client, un panier et un numéro de CB
     * 
     * @param credit_card 4 derniers chiffres de la carte banquaire
     * @param shoppingCart panier du client
     * @param user  utilisateur qui passe la commande
     */
    @Override
    public void addOrder(User user, Cart shoppingCart,String credit_card ) {
        Orders order = new Orders();
        Date date_ajout = new Date();
        order.setOrderDate(date_ajout);
        order.setOrderState((short)0);
        order.setOrderDateState(date_ajout);
        order.setOrderCreditCard(credit_card.substring(credit_card.length()-3, credit_card.length()));
        order.setUserUserId(user);
        em.persist(order);
        ArrayList<OrderRow> clctn = new ArrayList();
        for(CartItem item : shoppingCart.getItems()) {
            OrderRow row = new OrderRow();
            row.setBookBookId(item.getBook());
            row.setOrderOrderId(order);
            row.setOrderRowQuantity(item.getQuantity());
            row.setOrderRowPrice(item.getTotal());
            clctn.add(row);
        }
        order.setOrderRowCollection(clctn);
        em.merge(order);
    }

    /**
     * Récupère une liste de commandes selon un utilisateur
     * 
     * @param user  utilisateur qui passe la commande
     */
    @Override
    public List<Orders> getOrdersByUser(User user) {
        return em.createNamedQuery("Orders.findByUserId").setParameter("userId", user).getResultList();
    }

    /**
     * Récupère une liste de commandes selon un état actuel
     * 
     * @param state etat de commande ciblée
     */
    @Override
    public List<Orders> getOrdersByState(int state) {
        return em.createNamedQuery("Orders.findByOrderState").setParameter("orderState", state).getResultList();
    }

    /**
     * Récupère une commande par son Id
     * 
     * @param id  id de la commande ciblée
     */
    @Override
    public Orders getOrderById(int id) {
        try {
            return em.createNamedQuery("Orders.findByOrderId", Orders.class).setParameter("orderId", id).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    /**
     * Met à jour l'état d'une commande (plus date de mise à jour d'état)
     * 
     * @param id id de la commande ciblée
     * @param state nouvel état de la commande
     */
    @Override
    public void updateOrderById(int id, int state) {
        Orders o = em.createNamedQuery("Orders.findByOrderId", Orders.class).setParameter("orderId", id).getSingleResult();
        o.setOrderState((short)state);
        o.setOrderDateState(new Date());
        em.merge(o);
    }
    
    
}
