/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Book;
import com.booxtore.entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Matthieu P.
 */
@Stateless
public class BookAccessor implements BookAccessorLocal {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Booxtore-ejbPU");
    
    //TODO: remplacer par get from xml !
    private int number_books_displayed = 10;
    
    public BookAccessor() {
        
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    /**
     * Renvoie une liste de livres selon une catégorie
     * 
     * @param category id de la catégorie de livres // Entier de 1 à n
     * @param index numéro de la page de résultats à récupérer // Entier de 1 à n. Si 0, alors tous les résultats sont récupérés
     * @return Liste de livre selon la catégorie et l'index précisé
     */
    @Override
    public List<Book> getBooksByCategory(int category, int index) {
        
        // Création de l'e.m.
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT b FROM Book b LEFT JOIN b.categoryCategoryId c WHERE c.categoryId = :categoryId");
        q.setParameter("categoryId", category);
        if(index > 0 ) { 
            q.setFirstResult( ((index-1) * number_books_displayed));
            q.setMaxResults(number_books_displayed);
        }
        return q.getResultList();
    }

    /**
     * Renvoie une liste de catégories
     * 
     * @return Liste de catégories
     */
    @Override
    public List<Category> getCategories() {
        // Création de l'e.m.
        EntityManager em = emf.createEntityManager();
        return  em.createQuery("SELECT c FROM Category c LEFT JOIN c.bookCollection b WHERE b.bookId > 0 GROUP BY (c.categoryName)")
                                   .getResultList();
    }

    /**
     * Renvoie un livre  selon son id
     * 
     * @param id int id du livre à récupérer
     * @return Livre
     */
    @Override
    public Book getBook(int id) {
        // Création de l'e.m.
        EntityManager em = emf.createEntityManager();
        return em.find(Book.class, id);
    }
    
    
    /**
     * Renvoie une liste de livre  selon des mots-clés
     * 
     * @param keywords String keyword des livre à récupérer
     * @param index numéro de la page de résultats à récupérer // Entier de 1 à n
     * @return Liste Livre
     */
    @Override
    public List<Book> getBooksByKeywords(String keywords, int index) {
        // Création de l'e.m.
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT b FROM Book b WHERE b.categoryCategoryId.categoryName LIKE '%:keywords%'"
                + " OR b.categoryCategoryId.categoryKeywords LIKE '%:keywords%'"
                + " OR b.categoryCategoryId..categorySummary LIKE '%:keywords%'"
                + " OR b.editorEditorId.editorName LIKE '%:keywords%'"
                + " OR b.editorEditorId.editorSummary LIKE '%:keywords%'"
                + " OR b.bookName LIKE '%:keywords%'"
                + " OR b.bookSummary LIKE '%:keywords%'")
                .setParameter("keywords", keywords);
        if(index > 0 ) { 
            q.setFirstResult( ((index-1) * number_books_displayed));
            q.setMaxResults(number_books_displayed);
        }
        return q.getResultList();
    }
    
    /**
     * Renvoie une liste de livres
     * 
     * @param index numéro de la page de résultats à récupérer // Entier de 1 à n
     * @return 
     */
    @Override
    public List<Book> getBooks(int index) {
        EntityManager em = emf.createEntityManager();
        
        Query q = em.createNamedQuery("Book.findAll");
        if(index > 0 ) { 
            q.setFirstResult( ((index-1) * number_books_displayed));
            q.setMaxResults(number_books_displayed);
        }
        return q.getResultList();
    }

    /**
     * Renvoie une liste de livres qui dont le stock est inférieur au stock critique
     * @return la liste des livres qui sont à recommander
     */
    @Override
    public List<Book> getBooksToResupply() {
        // Création de l'e.m.
        EntityManager em = emf.createEntityManager();
        return  em.createQuery("SELECT b FROM Book b WHERE b.bookQuantity < b.bookThreshold OR b.bookState = 3")
                                   .getResultList();
    }
    
    
    /**
     * Renvoie une liste des livres les plus vendus
     * @return la liste des livres les plus populaires
     */
    @Override
    public List<Book> getTopBooks() {
        EntityManager em = emf.createEntityManager();
        return  em.createQuery("SELECT o.bookBookId FROM OrderRow o GROUP BY o.bookBookId ORDER BY SUM(o.orderRowQuantity)").setMaxResults(10).getResultList();
    }
}
