/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.business.BookManagerLocal;
import com.booxtore.entity.Book;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author netbean
 */
@ManagedBean
@RequestScoped
public class CatalogManagement {
    
    @EJB
    private BookManagerLocal bookManager;
    @EJB
    private BookAccessorLocal bookAccessor;

    
    /**
     * Creates a new instance of CatalogManagement
     */
    public CatalogManagement() {
    }
    
    /**
     * Liste tous les livres du catalogue
     * @return la liste des livres
     */
    public List<Book> listBooks() {
        return bookAccessor.getBooks(0);
    }
    
    /**
     * Récupère un livre selon son id
     * @param id id du livre à récupérer
     * @return le livre trouvé
     */
    public Book getBook(int id) {
        return bookAccessor.getBook(id);
    }
}
