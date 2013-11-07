/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.model;

import com.booxtore.entity.Book;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author J-Baptiste L.
 */
@Stateless
public class Cart implements CartLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    ArrayList<CartItem> contents;
    int numbersOfItems;
    double total;

    public Cart(){
        this.contents = new ArrayList<CartItem>();
    }
    
    @Override
    public void addBook(Book book) {
        boolean newItem = true;
        
        for(CartItem item : contents){
            if(item.getBook().getBookId() == book.getBookId()){
                newItem = false;
                item.incrementQuantity();
            }
        }
        
        if(newItem){
            CartItem item = new CartItem(book);
            contents.add(item);
        }
    }
    
    @Override
    public void updateBook(Book book, String quantity) {
        int qty = -1;
        
        qty = Integer.parseInt(quantity);
        
        if(qty >= 0){
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

    @Override
    public ArrayList<CartItem> getItems() {
        return null;
    }

    @Override
    public int getNumbersOfItems() {
        numbersOfItems = 0;
        
        for(CartItem item : contents){
            numbersOfItems += item.getQuantity();
        }
        
        return numbersOfItems;
    }

    @Override
    public double getSubtotal() {
        
        double amount = 0;
        
        for(CartItem item : contents){
            Book book = (Book) item.getBook();
            amount += (item.getQuantity() * book.getBookPrice());
        }
        
        return amount;
    }

    @Override
    public void calculateTotal(String surcharge) {
        double amount = 0;
        
        double s = Double.parseDouble(surcharge);
        
        amount = this.getSubtotal();
        amount += s;
        
        total = amount;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public void clear() {
        contents.clear();
        numbersOfItems = 0;
        total = 0;
    }
}