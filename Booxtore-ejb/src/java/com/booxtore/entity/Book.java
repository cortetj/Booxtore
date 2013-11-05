/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author netbean
 */
@Entity
@Table(name = "book", catalog = "booxtore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByBookId", query = "SELECT b FROM Book b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Book.findByCategoryId", query = "SELECT b FROM Book b WHERE b.categoryId = :categoryId LIMIT :index"),
    @NamedQuery(name = "Book.findByAuthorId", query = "SELECT b FROM Book b WHERE b.authorId = :authorId"),
    @NamedQuery(name = "Book.findByEditorId", query = "SELECT b FROM Book b WHERE b.editorId = :editorId"),
    @NamedQuery(name = "Book.findByBookName", query = "SELECT b FROM Book b WHERE b.bookName = :bookName"),
    @NamedQuery(name = "Book.findByBookSummary", query = "SELECT b FROM Book b WHERE b.bookSummary = :bookSummary"),
    @NamedQuery(name = "Book.findByBookReleaseDate", query = "SELECT b FROM Book b WHERE b.bookReleaseDate = :bookReleaseDate"),
    @NamedQuery(name = "Book.findByBookQuantity", query = "SELECT b FROM Book b WHERE b.bookQuantity = :bookQuantity"),
    @NamedQuery(name = "Book.findByBookThreshold", query = "SELECT b FROM Book b WHERE b.bookThreshold = :bookThreshold"),
    @NamedQuery(name = "Book.findByBookState", query = "SELECT b FROM Book b WHERE b.bookState = :bookState"),
    @NamedQuery(name = "Book.findByBookPrice", query = "SELECT b FROM Book b WHERE b.bookPrice = :bookPrice")})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_id")
    private Integer bookId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "category_id")
    private int categoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "author_id")
    private String authorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "editor_id")
    private int editorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "book_name")
    private String bookName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "book_summary")
    private String bookSummary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_release_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookReleaseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_quantity")
    private int bookQuantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_threshold")
    private int bookThreshold;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_state")
    private short bookState;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_price")
    private BigDecimal bookPrice;

    public Book() {
    }

    public Book(Integer bookId) {
        this.bookId = bookId;
    }

    public Book(Integer bookId, int categoryId, String authorId, int editorId, String bookName, String bookSummary, Date bookReleaseDate, int bookQuantity, int bookThreshold, short bookState, BigDecimal bookPrice) {
        this.bookId = bookId;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.editorId = editorId;
        this.bookName = bookName;
        this.bookSummary = bookSummary;
        this.bookReleaseDate = bookReleaseDate;
        this.bookQuantity = bookQuantity;
        this.bookThreshold = bookThreshold;
        this.bookState = bookState;
        this.bookPrice = bookPrice;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public Date getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(Date bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public int getBookThreshold() {
        return bookThreshold;
    }

    public void setBookThreshold(int bookThreshold) {
        this.bookThreshold = bookThreshold;
    }

    public short getBookState() {
        return bookState;
    }

    public void setBookState(short bookState) {
        this.bookState = bookState;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.booxtore.business.Book[ bookId=" + bookId + " ]";
    }
    
}
