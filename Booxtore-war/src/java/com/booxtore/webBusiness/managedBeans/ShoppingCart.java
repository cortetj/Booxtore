/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.model.Cart;
import com.booxtore.model.CartItem;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author netbean
 */
@ManagedBean
@SessionScoped
public class ShoppingCart {
    @EJB
    private BookAccessorLocal bookAccessor;
    
    Cart shoppingCart;
    
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
    
    public float priceCart(){
        return shoppingCart.getSubtotal();
    }
    
    public String clearCart() {
        shoppingCart.clear();
        return null;
    }
    
    /**
     * Effectue les calculs des totaux & cie.
     * @return total du panier 
     */
    public float updateCartinfos() {
        return shoppingCart.getSubtotal();
    }
}
