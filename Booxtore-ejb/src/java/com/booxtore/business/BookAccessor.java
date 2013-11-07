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

/**
 *
 * @author Matthieu P.
 */
@Stateless
public class BookAccessor implements BookAccessorLocal {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Booxtore-ejbPU");
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    /**
     * Renvoie une liste de livres selon une catégorie
     * @param category id de la catégorie de livres // Entier de 1 à n
     * @param index numéro de la page de résultats à récupérer // Entier de 1 à n
     * @return Liste de livre selon la catégorie et l'index précisé
     */
    @Override
    public List<Book> getBooksByCategory(int category, int index) {
        //TODO: remplacer par get from xml !
        int number_books_displayed = 10;
        
        // Création de l'e.m.
        EntityManager em = emf.createEntityManager();
        return  em.createNamedQuery("Book.findByCategoryId")
                                   .setParameter("categoryId", category)
                                   .setFirstResult( ((index-1) * number_books_displayed))
                                   .setMaxResults(number_books_displayed)
                                   .getResultList();
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
        return  em.createNamedQuery("Category.findAll")
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
        Book b = new Book();
        b = (Book)em.createNamedQuery("Book.findByBookId")
                  .setParameter("bookId", id)
                  .getSingleResult();
        System.out.println("PRINT BIG");
        return  b;
    }
    
    
    /**
     * Renvoie une liste de livre  selon des mots-clés
     * 
     * @param keywords String keyword des livre à récupérer
     * @return Liste Livre
     */
    @Override
    public List<Book> getBooksByKeywords(String keywords) {
        // Création de l'e.m.
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT b FROM Book b WHERE b.categoryCategoryId.categoryName LIKE '%:keywords%'"
                + " OR b.categoryCategoryId.categoryKeywords LIKE '%:keywords%'"
                + " OR b.categoryCategoryId.categorySummary LIKE '%:keywords%'"
                + " OR b.authorCollection.authorName LIKE '%:keywords%'"
                + " OR b.authorCollection.authorSummary LIKE '%:keywords%'"
                + " OR b.editorEditorId.editorName LIKE '%:keywords%'"
                + " OR b.editorEditorId.editorSummary LIKE '%:keywords%'"
                + " OR b.bookName LIKE '%:keywords%'"
                + " OR b.bookSummary LIKE '%:keywords%'")
                .setParameter("keywords", keywords)
                .getResultList();
    }

    @Override
    public List<Book> getBooks() {
        EntityManager em = emf.createEntityManager();
        return  em.createNamedQuery("Book.findAll")
                                   .getResultList();
    }
}