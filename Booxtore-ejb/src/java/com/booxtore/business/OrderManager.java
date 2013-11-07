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
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
     * Renvoie une liste de commandes selon un utilisateur
     * @param user  utilisateur connecté
     * @return Liste de commandes selon l'utilisateur précisé
     */
    @Override
    public List<Orders> getListOrder(User user) {
        return em.createNamedQuery("Orders.findByUserId")
                .setParameter("userUserId", user)
                .getResultList();
    }

     /**
     * Ajoute une commande selon une date, un état, une date d'état, un numéro de CB et un utilisateur
     * @param date date de la commande à ajouter
     * @param state numéro de l'état de la commande //Entier de 1 à 6
     * @param date_state  date de la dernier modification de l'état
     * @param credit_card 4 dernier numéro de la carte banquaire
     * @param user  utilisateur qui passe la commande
     */
    @Override
    public void addOrder(Date date, short state, Date date_state, String credit_card, User user) {
        Orders order = new Orders();
        order.setOrderDate(date);
        order.setOrderState(state);
        order.setOrderDateState(date_state);
        order.setOrderCreditCard(credit_card);
        order.setUserUserId(user);

        em.persist(order);
    }

    /**
     * Ajoute une ligne de commande selon une quantité, un livre et une commande
     * @param quantity quantité du livre commander
     * @param book le livre à commander
     * @param order la commande liée à la ligne
     */
    @Override
    public void addOrderRows(int quantity, Book book, Orders order) {
        OrderRow orderRow = new OrderRow();
        orderRow.setOrderRowQuantity(quantity);
        orderRow.setBookBookId(book);
        orderRow.setOrderOrderId(order);
        
        em.persist(orderRow);
    }
}
