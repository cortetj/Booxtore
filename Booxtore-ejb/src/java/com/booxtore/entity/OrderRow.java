/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author netbean
 */
@Entity
@Table(name = "order_row", catalog = "booxtore", schema = "")
@NamedQueries({
    @NamedQuery(name = "OrderRow.findAll", query = "SELECT o FROM OrderRow o"),
    @NamedQuery(name = "OrderRow.findByOrderRowId", query = "SELECT o FROM OrderRow o WHERE o.orderRowId = :orderRowId"),
    @NamedQuery(name = "OrderRow.findByOrderRowQuantity", query = "SELECT o FROM OrderRow o WHERE o.orderRowQuantity = :orderRowQuantity"),
    @NamedQuery(name = "OrderRow.findByOrderRowPrice", query = "SELECT o FROM OrderRow o WHERE o.orderRowPrice = :orderRowPrice"),
    @NamedQuery(name = "OrderRow.findByOrderId", query = "SELECT o FROM OrderRow o WHERE o.orderOrderId = :orderOrderId")})
public class OrderRow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_row_id")
    private Integer orderRowId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_row_quantity")
    private int orderRowQuantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_row_price")
    private float orderRowPrice;
    @JoinColumn(name = "order_order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Orders orderOrderId;
    @JoinColumn(name = "book_book_id", referencedColumnName = "book_id")
    @ManyToOne(optional = false)
    private Book bookBookId;

    public OrderRow() {
    }

    public OrderRow(Integer orderRowId) {
        this.orderRowId = orderRowId;
    }

    public OrderRow(Integer orderRowId, int orderRowQuantity, float orderRowPrice) {
        this.orderRowId = orderRowId;
        this.orderRowQuantity = orderRowQuantity;
        this.orderRowPrice = orderRowPrice;
    }
    
    public OrderRow(Book book, int orderRowQuantity){
        this.bookBookId = book;
        this.orderRowQuantity = orderRowQuantity;
    }

    public Integer getOrderRowId() {
        return orderRowId;
    }

    public void setOrderRowId(Integer orderRowId) {
        this.orderRowId = orderRowId;
    }

    public int getOrderRowQuantity() {
        return orderRowQuantity;
    }

    public void setOrderRowQuantity(int orderRowQuantity) {
        this.orderRowQuantity = orderRowQuantity;
    }

    public float getOrderRowPrice() {
        return orderRowPrice;
    }

    public void setOrderRowPrice(float orderRowPrice) {
        this.orderRowPrice = orderRowPrice;
    }

    public Orders getOrderOrderId() {
        return orderOrderId;
    }

    public void setOrderOrderId(Orders orderOrderId) {
        this.orderOrderId = orderOrderId;
    }

    public Book getBookBookId() {
        return bookBookId;
    }

    public void setBookBookId(Book bookBookId) {
        this.bookBookId = bookBookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderRowId != null ? orderRowId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderRow)) {
            return false;
        }
        OrderRow other = (OrderRow) object;
        if ((this.orderRowId == null && other.orderRowId != null) || (this.orderRowId != null && !this.orderRowId.equals(other.orderRowId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.booxtore.entity.OrderRow[ orderRowId=" + orderRowId + " ]";
    }
    
}
