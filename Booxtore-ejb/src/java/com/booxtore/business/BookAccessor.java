/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Book;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author netbean
 */
@Stateless
public class BookAccessor implements BookAccessorLocal {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Booxtore-ejbPU");
    
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
        return em.createNamedQuery("Book.findByCategoryId")
                                   .setParameter("categoryId", category)
                                   .setFirstResult( ((index-1) * number_books_displayed) + 1 )
                                   .setMaxResults(number_books_displayed)
                                   .getResultList();   
    }
    
}
