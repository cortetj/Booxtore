/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Author;
import com.booxtore.entity.Book;
import com.booxtore.entity.Category;
import com.booxtore.entity.Editor;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author J-Baptiste.L
 */
@Stateless
public class BookManager implements BookManagerLocal {
    @PersistenceContext(unitName = "Booxtore-ejbPU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    /**
     * Ajoute un livre au cataloque selon une catégorie, un auteur, un éditeur et tout ce qui le définis (nom, description, prix, quantité, date de parution, etat
     * @param categoryName nom de la catégorie
     * @param authorNameList tableau de nom d'auteur qui ont écrit le livre
     * @param editorName nom de l'editeur
     * @param bookName titre du livre
     * @param bookPrice prix unitaire HT du livre
     * @param bookQuantity quantité de livre en stock
     * @param bookReleaseDate date de parution du livre
     * @param bookState Etat du livre (nouveauté, en stock, à venir, en réaprovisionnement et indisponible)
     * @param bookSummary Résumé, description du livre
     * @return Id du livre ajouté
     */
    @Override
    public int addBook(String categoryName, ArrayList<String> authorNameList, String editorName, String bookName, float bookPrice, int bookQuantity, Date bookReleaseDate, short bookState, String bookSummary) {
        
        Book book = new Book();
        book.setBookName(bookName);
        book.setBookPrice(bookPrice);
        book.setBookQuantity(bookQuantity);
        book.setBookReleaseDate(bookReleaseDate);
        book.setBookState(bookState);
        book.setBookSummary(bookSummary);
        
        Category category = getCategory(categoryName);
        if(category != null){
            book.setCategoryCategoryId(category);
        } else {
            book.setCategoryCategoryId(addCategory(categoryName));
        }
        
        Editor editor = getEditor(editorName);
        if(editor != null){
            book.setEditorEditorId(editor);
        } else {
            book.setEditorEditorId(addEditor(editorName));
        }
        
        for (String authorName : authorNameList) {
            Author author = getAuthor(authorName);
            ArrayList<Author> nameList = new ArrayList<Author>();
            
            if(author != null){
                nameList.add(author);
            } else {
                nameList.add(addAuthor(authorName));
            }
            
            book.setAuthorCollection(nameList);
        }
        
        em.persist(book);
        
        return book.getBookId();
    }
    
    
    /**
     * Met à jour un livre au cataloque selon son Id en modifiant: une catégorie, un auteur, un éditeur et tout ce qui le définis (nom, description, prix, quantité, date de parution, etat
     * @param categoryName nom de la catégorie
     * @param authorNameList tableau de nom d'auteur qui ont écrit le livre
     * @param editorName nom de l'editeur
     * @param bookName titre du livre
     * @param bookPrice prix unitaire HT du livre
     * @param bookQuantity quantité de livre en stock
     * @param bookReleaseDate date de parution du livre
     * @param bookState Etat du livre (nouveauté, en stock, à venir, en réaprovisionnement et indisponible)
     * @param bookSummary Résumé, description du livre
     * @return Id du livre mis à jour
     */
    @Override
    public int updateBook(String categoryName, ArrayList<String> authorNameList, String editorName, int bookId, String bookName, float bookPrice, int bookQuantity, Date bookReleaseDate, short bookState, String bookSummary) {
        
        Book book = em.createNamedQuery("Book.findByBookId", Book.class).setParameter("bookId", bookId).getSingleResult();
        book.setBookName(bookName);
        book.setBookPrice(bookPrice);
        book.setBookQuantity(bookQuantity);
        book.setBookReleaseDate(bookReleaseDate);
        book.setBookState(bookState);
        book.setBookSummary(bookSummary);
        
        Category category = getCategory(categoryName);
        if(category != null){
            book.setCategoryCategoryId(category);
        } else {
            book.setCategoryCategoryId(addCategory(categoryName));
        }
        
        Editor editor = getEditor(editorName);
        if(editor != null){
            book.setEditorEditorId(editor);
        } else {
            book.setEditorEditorId(addEditor(editorName));
        }
        
        for (String authorName : authorNameList) {
            Author author = getAuthor(authorName);
            ArrayList<Author> nameList = new ArrayList<Author>();
            
            if(author != null){
                nameList.add(author);
            } else {
                nameList.add(addAuthor(authorName));
            }
            
            book.setAuthorCollection(nameList);
        }
        
        em.merge(book);
        
        return book.getBookId();
    }
    
    
    /**
     * Récupère une Catégorie selon son nom
     * @param categoryName nom de la catégorie
     * @return La Catégorie trouvée
     */
    @Override
    public Category getCategory(String categoryName) {
        return em.createNamedQuery("Category.findByCategoryName", Category.class)
                .setParameter("categoryName", categoryName)
                .getSingleResult();
    }
    
    /**
     * Ajoute une Catégorie
     * @param name  nom de la catégorie
     * @return La Catégorie ajoutée
     */
    @Override
    public Category addCategory(String name){
        Category category = new Category();
        category.setCategoryName(name);
        em.persist(category);
        
        return category;
    }
    
    /**
     * Met à jour une Catégorie selon son id
     * @param categoryId  Id de la catégorie
     * @param name nom de la categorie
     * @return un boolean selon le résultat de la requète
     */
    @Override
    public boolean updateCategorie(int categoryId, String name) {
        int query = em.createQuery ("UPDATE Category c SET c.categoryName = :name WHERE c.categoryId = :categoryId")
                .setParameter("name", name)
                .executeUpdate ();
        if(query > 0){
            return true;
        }
               
        return false;
    }

    /**
     * Récupère un Autheur selon son nom
     * @param authorName nom de l'Autheur
     * @return L'Autheur trouvé
     */
    @Override
    public Author getAuthor(String authorName) {
        return em.createNamedQuery("Author.findByAuthorName", Author.class)
                .setParameter("authorName", authorName)
                .getSingleResult();
    }

    /**
     * Ajoute un Autheur
     * @param authorName   nom de l'autheur
     * @return L'autheur ajouté
     */
    @Override
    public Author addAuthor(String authorName) {
        Author author = new Author();
        author.setAuthorName(authorName);
        em.persist(author);
        
        return author;
    }

    
    /**
     * Met à jour un Auteur selon son id
     * @param authorId Id de l'auteur
     * @param name nom de l'auteur
     * @return un boolean selon le résultat de la requète
     */
    @Override
    public boolean updateAuthor(int authorId, String name) {
        int query = em.createQuery ("UPDATE Author a SET a.authorName = :name WHERE a.authorId = :authorId")
                .setParameter("name", name)
                .executeUpdate ();
        if(query > 0){
            return true;
        }
               
        return false;
    }

    /**
     * Récupère un Editeur selon son nom
     * @param editorName  nom de l'Editeur
     * @return L'Editeur trouvé
     */
    @Override
    public Editor getEditor(String editorName) {
        return em.createNamedQuery("Editor.findByEditorName", Editor.class)
                .setParameter("editorName", editorName)
                .getSingleResult();
    }

    /**
     * Ajoute un Editeur
     * @param editorName  nom de l'Editeur
     * @return L'Editeur ajouté
     */
    @Override
    public Editor addEditor(String editorName) {
        Editor editor = new Editor();
        editor.setEditorName(editorName);
        em.persist(editor);
        
        return editor;
    }

    
    /**
     * Met à jour un Editeur selon son id
     * @param editorId Id de l'Editeur
     * @param name nom de l'Editeur
     * @return un boolean selon le résultat de la requète
     */
    @Override
    public boolean updateEditor(int editorId, String name) {
        int query = em.createQuery ("UPDATE Editor e SET e.editorName = :name WHERE e.editorId = :editorId")
                .setParameter("name", name)
                .executeUpdate ();
        if(query > 0){
            return true;
        }
               
        return false;
    }   
}
