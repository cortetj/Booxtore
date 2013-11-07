/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.model;

import com.booxtore.entity.Book;

/**
 *
 * @author J-Baptiste L.
 */
public class CartItem {
    Book book;
    int quantity;
    
    public CartItem(Book book){
        this.book = book;
        quantity = 1;
    }
    
    public Book getBook(){
        return book;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public  void incrementQuantity(){
        quantity++;
    }
    
    public void decrementQuantity(){
        quantity--;
    }
    
    public double getTotal(){
        double amount = 0;
        amount = (this.getQuantity() * book.getBookPrice());
        return amount;
    }
}
