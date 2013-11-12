/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.webBusiness.managedBeans;

import com.booxtore.business.BookAccessorLocal;
import com.booxtore.entity.Book;
import com.booxtore.entity.Category;
import com.sun.istack.logging.Logger;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
public class Books {
    @EJB
    private BookAccessorLocal bookAccessor;
    
    // Utile pour la recherche de livres
    private String keywords = "Recherchez ici !";
    private List<Book> books;
    
    private int categoryId;
    private String categoryName;
    private String categorySummary;
    private String categoryKeywords;

    /**
     * Creates a new instance of Books
     */
    public Books() {
        keywords = "Recherchez ici !";
    }
    

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public Category getCategoryById(int id){
        return bookAccessor.getCategoryById(id);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySummary() {
        return categorySummary;
    }

    public void setCategorySummary(String categorySummary) {
        this.categorySummary = categorySummary;
    }

    public String getCategoryKeywords() {
        return categoryKeywords;
    }

    public void setCategoryKeywords(String categoryKeywords) {
        this.categoryKeywords = categoryKeywords;
    }
    
    
    
    public void updateCategory(){
        System.out.println(categoryId);
        System.out.println(categoryName);
        System.out.println(categorySummary);
        System.out.println(categoryKeywords);

        bookAccessor.updateCategory(categoryId, categoryName, categorySummary, categoryKeywords);
         ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
        try{
            context.redirect(context.getRequestContextPath()+"/admin_area/category.html?id=0");
        } catch (IOException e){
            System.err.println("Echec de la mise à jour de la catégorie");
        }
        
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
     * @return  une liste de livres
     */
    public List<Book> listBooksByKeywords(String search){
        return bookAccessor.getBooksByKeywords(search, 0);
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
 
    public List<Book> listBooksToResupply() {
        return bookAccessor.getBooksToResupply();
    }
    
    public String searchByKeywords() {
        if( keywords != null && !keywords.isEmpty() && !keywords.equals("Recherchez ici !") ) {
            //ExternalContext context =  FacesContext.getCurrentInstance().getExternalContext();
            //try {
            //    context.redirect(context.getRequestContextPath()+"/books.html?search="+keywords.replace(" ", "\20"));
            //} catch (IOException ex) {
            //    Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            //}
            return "books.html?faces-redirect=true&search="+keywords;
        }
        return null;
    }
    
    public List<Book> loadBooks(Map<String, String> e) {
        if(e.containsKey("search")) {
           return listBooksByKeywords(e.get("search"));
        }
        else if(e.containsKey("category")) {
           int i = Integer.parseInt(e.get("category"));
           return listBooksByCategory(i,0); 
        }
        else {
           return listBooks(0); 
        }
    }
    
    public void loadCategory(int idCategory){
        Category c = null;
        if( idCategory > 0 ) c = bookAccessor.getCategoryById(idCategory);
        else {
            categoryId = 0;
        }
        
        if(c != null){
            categoryId = idCategory;
            categoryName = c.getCategoryName();
            categorySummary = c.getCategorySummary();
            categoryKeywords = c.getCategoryKeywords();
        }
    }
}
