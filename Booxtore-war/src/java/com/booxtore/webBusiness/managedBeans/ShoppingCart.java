/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.business.OrderManagerLocal;
import com.booxtore.entity.Book;
import com.booxtore.model.Cart;
import com.booxtore.model.CartItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author netbean
 */
@ManagedBean
@SessionScoped
public class ShoppingCart  implements Serializable{
    @EJB
    private OrderManagerLocal orderManager;
    @EJB
    private BookAccessorLocal bookAccessor;
    
    
    Cart shoppingCart;

    String creditcard;
    String code;
    Date date;

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    /**
     * Creates a new instance of ShoppingCart
     */
    public ShoppingCart() {
        shoppingCart = new Cart();
    }
    
    /**
     * Compte le nombre de livre dans la panier
     * 
     * @return le nombre de livre
     */    
    public int countCartItem(){
        return shoppingCart.getNumbersOfItems();
    }
    
    /**
     * Liste les livres du panier
     * 
     * @return la liste des livres
     */
    public ArrayList<CartItem> listCartBook(){
        return shoppingCart.getItems();
    }
    
    /**
     * Ajoute un livre au panier
     * 
     * @return null
     */    
    public String addBook() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	int id = Integer.parseInt(params.get("id"));
        Book b = bookAccessor.getBook(id);
        if(b.getBookQuantity() > 0 ) {
            shoppingCart.addBook(b, 1);
        }
        return null;
    }
    
    /**
     * Mise à jour de la quantité d'un livre dans le panier
     * @param id id du livre à mettre à jour
     * @param quantity la quantité à ajouté
     * @return null
     */
    public String updateBook(int id, int quantity) {
        shoppingCart.updateBook(bookAccessor.getBook(id), quantity);
        return null;
    }
    
    /**
     * Supprime le livre du panier
     * @param id id du livre à supprimer
     * @return null 
     */
    public String delBook(int id) {
        shoppingCart.delBook(id);
        return null;
    }
    
    /**
     * Prix total du panier
     * 
     * @return le prix du panier
     */
    public float priceCart(){
        return shoppingCart.getSubtotal();
    }
    
    /**
     * Vide le panier
     * 
     * @return null
     */
    public String clearCart() {
        shoppingCart = new Cart();
        creditcard = null;
        code = null;
        date = null;
            
        return null;
    }
    
    /**
     * Effectue les calculs des totaux & cie.
     * @return total du panier 
     */
    public float updateCartinfos() {
        return shoppingCart.getSubtotal();
    }
    
    
    /**
     * Finalise le paiement de la commande et l'ajoute à la base de données
     * 
     * @return null - Redirige vers le recap de la commande
     */
    public String buyCart() {
        Auth user = (Auth) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auth"); 
        if( date.after(new Date()) == true ) {
            if( creditcard.length() > 9) { 
                orderManager.addOrder(user.getUser(), shoppingCart, creditcard);
                this.clearCart();
                ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
                try {
                    context.redirect(context.getRequestContextPath()+"/index.html?state=true");
                } catch (IOException ex) {
                    Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
