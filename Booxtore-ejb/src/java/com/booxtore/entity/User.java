/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.booxtore.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author netbean
 */
@Entity
@Table(name = "user", catalog = "booxtore", schema = "")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserFirstname", query = "SELECT u FROM User u WHERE u.userFirstname = :userFirstname"),
    @NamedQuery(name = "User.findByUserLastname", query = "SELECT u FROM User u WHERE u.userLastname = :userLastname"),
    @NamedQuery(name = "User.findByUserMail", query = "SELECT u FROM User u WHERE u.userMail = :userMail"),
    @NamedQuery(name = "User.findByUserAddress", query = "SELECT u FROM User u WHERE u.userAddress = :userAddress"),
    @NamedQuery(name = "User.findByUserCity", query = "SELECT u FROM User u WHERE u.userCity = :userCity"),
    @NamedQuery(name = "User.findByUserCityNumber", query = "SELECT u FROM User u WHERE u.userCityNumber = :userCityNumber"),
    @NamedQuery(name = "User.findByUserLogin", query = "SELECT u FROM User u WHERE u.userLogin = :userLogin"),
    @NamedQuery(name = "User.findByUserPassword", query = "SELECT u FROM User u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "User.findByUserState", query = "SELECT u FROM User u WHERE u.userState = :userState")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "user_firstname")
    private String userFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "user_lastname")
    private String userLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "user_mail")
    private String userMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "user_address")
    private String userAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "user_city")
    private String userCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "user_city_number")
    private String userCityNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "user_login")
    private String userLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "user_password")
    private String userPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_state")
    private short userState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userUserId")
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "user_group_user_group_id", referencedColumnName = "user_group_id")
    @ManyToOne(optional = false)
    private UserGroup userGroupUserGroupId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String userFirstname, String userLastname, String userMail, String userAddress, String userCity, String userCityNumber, String userLogin, String userPassword, short userState) {
        this.userId = userId;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userMail = userMail;
        this.userAddress = userAddress;
        this.userCity = userCity;
        this.userCityNumber = userCityNumber;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userState = userState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserCityNumber() {
        return userCityNumber;
    }

    public void setUserCityNumber(String userCityNumber) {
        this.userCityNumber = userCityNumber;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public short getUserState() {
        return userState;
    }

    public void setUserState(short userState) {
        this.userState = userState;
    }

    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public UserGroup getUserGroupUserGroupId() {
        return userGroupUserGroupId;
    }

    public void setUserGroupUserGroupId(UserGroup userGroupUserGroupId) {
        this.userGroupUserGroupId = userGroupUserGroupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.booxtore.entity.User[ userId=" + userId + " ]";
    }
    
}
