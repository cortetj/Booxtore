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
import java.util.ArrayList;
import java.util.Date;
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
public class ShoppingCart {
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
    
    public int countCartItem(){
        return shoppingCart.getNumbersOfItems();
    }
    
    public ArrayList<CartItem> listCartBook(){
        return shoppingCart.getItems();
    }
    
    public String addBook(int id, int quantity) {
        shoppingCart.addBook(bookAccessor.getBook(id), quantity);
        return null;
    }
    
    public String updateBook(int id, int quantity) {
        shoppingCart.updateBook(bookAccessor.getBook(id), quantity);
        return null;
    }
    
    public String delBook(int id) {
        shoppingCart.delBook(id);
        return null;
    }
    
    
    public float priceCart(){
        return shoppingCart.getSubtotal();
    }
    
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
     * 
     */
    public String buyCart() {
        Auth user = (Auth) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auth"); 
        if( date.after(new Date()) == true ) {
            if( creditcard.length() > 9) { 
                orderManager.addOrder(user.getUser(), shoppingCart, creditcard);
                this.clearCart();
                ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
                try {
                    context.redirect(context.getRequestContextPath()+"/secured_area/payment.html?state=true");
                } catch (IOException ex) {
                    Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
