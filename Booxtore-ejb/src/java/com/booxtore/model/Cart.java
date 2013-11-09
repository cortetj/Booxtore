/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.model;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.entity.Book;
import java.util.ArrayList;
import javax.ejb.EJB;

/**
 *
 * @author J-Baptiste L.
 */
public class Cart {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    ArrayList<CartItem> contents;
    int numbersOfItems;
    float total;

    public Cart(){
        this.contents = new ArrayList<CartItem>();
    }
    
    public void addBook(Book book, int quantity) {
        boolean newItem = true;
        
        for(CartItem item : contents){
            if(item.getBook().getBookId() == book.getBookId()){
                newItem = false;
                item.setQuantity( item.quantity + quantity );
                
                if(item.getQuantity() <= 0){
                    item = null;
                }
            }
            
        }
        if(newItem){
            CartItem item = new CartItem(book, quantity);
            contents.add(item);
        }
    }
    
    
    public void updateBook(Book book, int qty) {
        if(qty != 0){
            CartItem item = null;
            
            for(CartItem cartItem : contents){
                if(cartItem.getBook().getBookId() == book.getBookId()){
                    if(qty != 0){
                        cartItem.setQuantity(qty);
                    }else{
                        item = cartItem;
                        break;
                    }
                }
            }
            if(item != null){
                contents.remove(item);
            }
        }
    }
    
    
    public void delBook(int id) {
        ArrayList<CartItem> contentsTmp = new ArrayList<CartItem>();
        for(CartItem item : contents){
            if(item.getBook().getBookId() != id){
                contentsTmp.add(item);
            }
        }
        
        contents = contentsTmp;
    }
    
    public ArrayList<CartItem> getItems() {
        return contents;
    }

    
    public int getNumbersOfItems() {
        numbersOfItems = 0;
        
        for(CartItem item : contents){
            numbersOfItems += item.getQuantity();
        }
        
        return numbersOfItems;
    }

    
    public float getSubtotal() {
        
        float amount = 0;
        
        for(CartItem item : contents){
            Book book = (Book) item.getBook();
            amount += (item.getQuantity() * book.getBookPrice());
        }
        
        return amount;
    }
    
    
    public void calculateTotal(String surcharge) {
        float amount = 0;
        
        float s = Float.parseFloat(surcharge);
        
        amount = this.getSubtotal();
        amount += s;
        
        total = amount;
    }

    
    public float getTotal() {
        return total;
    }

    
    public void clear() {
        contents.clear();
        numbersOfItems = 0;
        total = 0;
    }

}