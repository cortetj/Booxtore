/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Book;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author netbean
 */
@Local
public interface CartLocal {
    public void initialize(String person);
    public void addBook(Book book);
    public void removeBook(Book book);
    public List<Book> getContents();
    public void remove();
}