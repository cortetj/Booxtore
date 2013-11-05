/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "OrderRow.findByOrderId", query = "SELECT o FROM OrderRow o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrderRow.findByBookId", query = "SELECT o FROM OrderRow o WHERE o.bookId = :bookId"),
    @NamedQuery(name = "OrderRow.findByOrderRowQuantity", query = "SELECT o FROM OrderRow o WHERE o.orderRowQuantity = :orderRowQuantity")})
public class OrderRow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_row_id")
    private Integer orderRowId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_id")
    private int orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_id")
    private int bookId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_row_quantity")
    private int orderRowQuantity;

    public OrderRow() {
    }

    public OrderRow(Integer orderRowId) {
        this.orderRowId = orderRowId;
    }

    public OrderRow(Integer orderRowId, int orderId, int bookId, int orderRowQuantity) {
        this.orderRowId = orderRowId;
        this.orderId = orderId;
        this.bookId = bookId;
        this.orderRowQuantity = orderRowQuantity;
    }

    public Integer getOrderRowId() {
        return orderRowId;
    }

    public void setOrderRowId(Integer orderRowId) {
        this.orderRowId = orderRowId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getOrderRowQuantity() {
        return orderRowQuantity;
    }

    public void setOrderRowQuantity(int orderRowQuantity) {
        this.orderRowQuantity = orderRowQuantity;
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
        return "com.booxtore.business.OrderRow[ orderRowId=" + orderRowId + " ]";
    }
    
}
