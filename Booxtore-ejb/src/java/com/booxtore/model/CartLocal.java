/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.model;

import com.booxtore.entity.Book;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author J-Baptiste L.
 */
@Local
public interface CartLocal {
    public void addBook(Book book);
    void updateBook(Book book, String quantity);
    ArrayList<CartItem> getItems();

    int getNumbersOfItems();

    double getSubtotal();

    void calculateTotal(String surcharge);

    double getTotal();

    void clear();
}