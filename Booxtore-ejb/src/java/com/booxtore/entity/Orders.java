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
@Table(name = "order", catalog = "booxtore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderId", query = "SELECT o FROM Orders o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Orders.findByOrderState", query = "SELECT o FROM Orders o WHERE o.orderState = :orderState"),
    @NamedQuery(name = "Orders.findByOrderDateState", query = "SELECT o FROM Orders o WHERE o.orderDateState = :orderDateState"),
    @NamedQuery(name = "Orders.findByOrderCreditCard", query = "SELECT o FROM Orders o WHERE o.orderCreditCard = :orderCreditCard"),
    @NamedQuery(name = "Orders.findByUserId", query = "SELECT o FROM Orders o WHERE o.userUserId = :userUserId")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_state")
    private short orderState;
    @Column(name = "order_date_state")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDateState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "order_credit_card")
    private String orderCreditCard;
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderOrderId")
    private Collection<OrderRow> orderRowCollection;

    public Orders() {
    }

    public Orders(Integer orderId) {
        this.orderId = orderId;
    }

    public Orders(Integer orderId, Date orderDate, short orderState, String orderCreditCard) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderState = orderState;
        this.orderCreditCard = orderCreditCard;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public short getOrderState() {
        return orderState;
    }

    public void setOrderState(short orderState) {
        this.orderState = orderState;
    }

    public Date getOrderDateState() {
        return orderDateState;
    }

    public void setOrderDateState(Date orderDateState) {
        this.orderDateState = orderDateState;
    }

    public String getOrderCreditCard() {
        return orderCreditCard;
    }

    public void setOrderCreditCard(String orderCreditCard) {
        this.orderCreditCard = orderCreditCard;
    }

    public User getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(User userUserId) {
        this.userUserId = userUserId;
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
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.booxtore.entity.Orders[ orderId=" + orderId + " ]";
    }
    
}
