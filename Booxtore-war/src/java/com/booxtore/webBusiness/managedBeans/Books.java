/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.entity.Book;
import com.booxtore.entity.Category;
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
public class Books {
    @EJB
    private BookAccessorLocal bookAccessor;
    
    // Utile pour la recherche de livres
    private String keywords;
    
    /**
     * Creates a new instance of Books
     */
    public Books() {
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    /**
     * Récupération des catégories
     * @return liste de catégories
     */
    public List<Category> listCategories(){
        return bookAccessor.getCategories();
    }
    
    /**
     * Récupération des livres
     * 
     * @param page page de résultat à retourner
     * @return  une liste de livres
     */
    public List<Book> listBooks(int page) {
        return bookAccessor.getBooks(page);
    }
    
    /**
     * Recherche de livres par mots clefs, ces derniers enregistrés dans String keywords
     * 
     * @param page page de résultat à retourner
     * @return  une liste de livres
     */
    public List<Book> listBooksByKeywords(int page){
        return bookAccessor.getBooksByKeywords(keywords, page);
    }
    
    /**
     * Renvoie une liste de livres selon une catégorie et une page de résultats
     * 
     * @param categoryId ID de la catégorie
     * @param page page de résultat à retourner
     * @return  une liste de livres
     */
    public List<Book> listBooksByCategory(int categoryId, int page) {
        return bookAccessor.getBooksByCategory(categoryId, page);
    }
    
}
