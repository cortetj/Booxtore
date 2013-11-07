/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author netbean
 */
@Stateful
public class Cart implements CartLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    String customerName;
    String customerId;
    List<Book> contents;

    @Override
    public void initialize(String person){
        if (person != null) {
            customerName = person;
        }

        customerId = "0";
        contents = new ArrayList<Book>();
    }
    
    @Override
    public void addBook(Book book) {
        contents.add(book);
    }

    @Override
    public void removeBook(Book book){
        contents.remove(book);
    }

    @Override
    public List<Book> getContents() {
        return contents;
    }

    @Remove
    @Override
    public void remove() {
        contents = null;
    }
}
