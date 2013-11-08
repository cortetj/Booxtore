/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.entity.Book;
import com.booxtore.entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author netbean
 */
@ManagedBean(name = "testManagedBean")
@RequestScoped
public class testManagedBean implements Serializable {
    @EJB
    private BookAccessorLocal bookAccessor;
    
    /**
     * Creates a new instance of testManagedBean
     */
    public testManagedBean() {
    }
    
    public List<Category> listCategories(){
        return bookAccessor.getCategories();
    }
    
    public List<Book> listBooks() {
        return bookAccessor.getBooks(1);
    }
    
    public List<Book> listBooksByKeywords(){
        return bookAccessor.getBooksByKeywords("author4",1);
    }
}
