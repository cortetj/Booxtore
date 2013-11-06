/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Book.findByCategoryId", query = "SELECT b FROM Book b WHERE b.categoryCategoryId.categoryId = :categoryId"),
    @NamedQuery(name = "Book.findByBookName", query = "SELECT b FROM Book b WHERE b.bookName = :bookName"),
    @NamedQuery(name = "Book.findByBookSummary", query = "SELECT b FROM Book b WHERE b.bookSummary = :bookSummary"),
    @NamedQuery(name = "Book.findByBookReleaseDate", query = "SELECT b FROM Book b WHERE b.bookReleaseDate = :bookReleaseDate"),
    @NamedQuery(name = "Book.findByBookQuantity", query = "SELECT b FROM Book b WHERE b.bookQuantity = :bookQuantity"),
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
    @Column(name = "book_state")
    private short bookState;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_price")
    private int bookPrice;
    @JoinTable(name = "book_has_author", joinColumns = {
        @JoinColumn(name = "book_book_id", referencedColumnName = "book_id")}, inverseJoinColumns = {
        @JoinColumn(name = "author_author_id", referencedColumnName = "author_id")})
    @ManyToMany
    private Collection<Author> authorCollection;
    @JoinColumn(name = "editor_editor_id", referencedColumnName = "editor_id")
    @ManyToOne(optional = false)
    private Editor editorEditorId;
    @JoinColumn(name = "category_category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private Category categoryCategoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookBookId")
    private Collection<OrderRow> orderRowCollection;

    public Book() {
    }

    public Book(Integer bookId) {
        this.bookId = bookId;
    }

    public Book(Integer bookId, String bookName, String bookSummary, Date bookReleaseDate, int bookQuantity, short bookState, int bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookSummary = bookSummary;
        this.bookReleaseDate = bookReleaseDate;
        this.bookQuantity = bookQuantity;
        this.bookState = bookState;
        this.bookPrice = bookPrice;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public short getBookState() {
        return bookState;
    }

    public void setBookState(short bookState) {
        this.bookState = bookState;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Collection<Author> getAuthorCollection() {
        return authorCollection;
    }

    public void setAuthorCollection(Collection<Author> authorCollection) {
        this.authorCollection = authorCollection;
    }

    public Editor getEditorEditorId() {
        return editorEditorId;
    }

    public void setEditorEditorId(Editor editorEditorId) {
        this.editorEditorId = editorEditorId;
    }

    public Category getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public void setCategoryCategoryId(Category categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public Collection<OrderRow> getOrderRowCollection() {
        return orderRowCollection;
    }

    public void setOrderRowCollection(Collection<OrderRow> orderRowCollection) {
        this.orderRowCollection = orderRowCollection;
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
        return "com.booxtore.entity.Book[ bookId=" + bookId + " ]";
    }
    
}
