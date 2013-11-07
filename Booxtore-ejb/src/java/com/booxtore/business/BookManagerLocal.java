/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.business;

import com.booxtore.entity.Author;
import com.booxtore.entity.Category;
import com.booxtore.entity.Editor;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author netbean
 */
@Local
public interface BookManagerLocal {

    Category getCategory(String categoryName);

    Category addCategory(String name);

    boolean updateCategory(int categoryId, String name);

    Author getAuthor(String authorName);

    Author addAuthor(String authorName);

    boolean updateAuthor(int authorId, String name);

    Editor getEditor(String editorName);

    Editor addEditor(String editorName);

    boolean updateEditor(int editorId, String name);

    int addBook(String categoryName, ArrayList<String> authorNameList, String editorName, String bookName, float bookPrice, int bookQuantity, Date bookReleaseDate, short bookState, String bookSummary);

    int updateBook(String categoryName, ArrayList<String> authorNameList, String editorName, int bookId, String bookName, float bookPrice, int bookQuantity, Date bookReleaseDate, short bookState, String bookSummary);

    
}
