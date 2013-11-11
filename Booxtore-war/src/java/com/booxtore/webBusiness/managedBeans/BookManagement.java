/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.business.BookManagerLocal;
import com.booxtore.entity.Author;
import com.booxtore.entity.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author netbean
 */
@ManagedBean
@RequestScoped
public class BookManagement {
    @EJB
    private BookAccessorLocal bookAccessor;
    @EJB
    private BookManagerLocal bookManager;

    public BookAccessorLocal getBookAccessor() {
        return bookAccessor;
    }

    public void setBookAccessor(BookAccessorLocal bookAccessor) {
        this.bookAccessor = bookAccessor;
    }

    public BookManagerLocal getBookManager() {
        return bookManager;
    }

    public void setBookManager(BookManagerLocal bookManager) {
        this.bookManager = bookManager;
    }
    
    
    private int id;
    private String name;
    private String summary;
    private float price;
    private int quantity;
    private int threshold;
    private Date date_release;
    private int state;
    private String author;
    private String category;
    private String editor;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public Date getDate_release() {
        return date_release;
    }

    public void setDate_release(Date date_release) {
        this.date_release = date_release;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
    
    
    
    /**
     * Creates a new instance of BookManagement
     */
    public BookManagement() {
    
    }
    
    /**
     * Charge les données relatives au livre à afficher. Si id = 0, on cherche à ajouter un livre
     * @param idBook 
     */
    public void loadBook(int idBook) {
        Book b = null;
        if ( idBook > 0 ) b = bookAccessor.getBook(idBook);
        else {
            id  = 0;
            date_release = new Date();
        }
        
        if(b != null) {
            id = idBook;
            name = b.getBookName();
            summary = b.getBookSummary();
            price = b.getBookPrice();
            quantity = b.getBookQuantity();
            threshold = b.getBookThreshold();
            date_release = b.getBookReleaseDate();
            state = b.getBookState();
            author = "";
            for(Author a : b.getAuthorCollection()) {
                if( !author.isEmpty() )  {
                    author+=",";
                }
                author += a.getAuthorName();
            }
            category = b.getCategoryCategoryId().getCategoryName();
            editor = b.getEditorEditorId().getEditorName();
        }
    }
    
    /**
     * Mise à jour du livre chargé sur la page
     * 
     * @return null - cette fonction redirige vers le cataloge en cas de succès / retourne la même page en cas d'échec
     */
    public String updateBook() {
        
        ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
        ArrayList arl = new ArrayList(Arrays.asList(author.split(",")));
        
        if(id == 0) {
            if(quantity > threshold) {
                bookManager.addBook(category, arl, editor, name, price, quantity, threshold, date_release, (short)0, summary);
            }
        }else {
            bookManager.updateBook(category, arl, editor, id, name, price, quantity, threshold, date_release, (short)state, summary);
        }
        
        try {
            
            context.redirect(context.getRequestContextPath()+"/admin_area/catalog.html");
        } catch (IOException e) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, e);
            System.err.println("Echec de la mise à jour / insertion du livre.");
        }
        return null;
    }
}
