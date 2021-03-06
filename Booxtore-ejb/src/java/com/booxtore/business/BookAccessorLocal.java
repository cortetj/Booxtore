/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Book;
import com.booxtore.entity.Category;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author netbean
 */
@Local
public interface BookAccessorLocal {
    List<Book> getBooksByCategory(int category, int index);

    List<Category> getCategories();

    Book getBook(int id);

    List<Book> getBooksByKeywords(String keywords, int index);
    
    List<Book> getBooks(int index);

    List<Book> getTopBooks();
    
    List<Book> getBooksToResupply();

    Category getCategoryById(int id);

    void updateCategory(int id, String name, String summary, String keywords);
    
    
}
