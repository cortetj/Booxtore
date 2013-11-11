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
    
    /**
     * Ajoute un livre au panier
     * @param book livre à ajouter
     * @param quantity quantité à ajouter
     */
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
    
    /**
     * Met à jour la quantité d'un livre du panier
     * @param book livre à mettre à jour
     * @param qty quantité à ajouter
     */
    public void updateBook(Book book, int qty) {
        if(qty != 0){
            CartItem item = null;
            
            for(CartItem cartItem : contents){
                if(cartItem.getBook().getBookId() == book.getBookId()){
                    if(qty != 0){
                        cartItem.setQuantity(cartItem.quantity + qty);
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
    
    /**
     * Supprime un livre du panier
     * @param id id du book à supprimer
     */
    public void delBook(int id) {
        ArrayList<CartItem> contentsTmp = new ArrayList<CartItem>();
        for(CartItem item : contents){
            if(item.getBook().getBookId() != id){
                contentsTmp.add(item);
            }
        }
        
        contents = contentsTmp;
    }
    
    /**
     * Renvoie la liste des produits du panier
     * @return la liste des produits
     */
    public ArrayList<CartItem> getItems() {
        return contents;
    }

    /**
     * Renvoie le nombre de produit du panier
     * @return le nombre de produit
     */
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

    /**
     * Retourne le prix total du panier
     * @return le prix du panier
     */
    public float getTotal() {
        return total;
    }

    /**
     * Vide le panier
     */
    public void clear() {
        contents.clear();
        numbersOfItems = 0;
        total = 0;
    }

}