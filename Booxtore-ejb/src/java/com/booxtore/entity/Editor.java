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
@Table(name = "editor", catalog = "booxtore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Editor.findAll", query = "SELECT e FROM Editor e"),
    @NamedQuery(name = "Editor.findByEditorId", query = "SELECT e FROM Editor e WHERE e.editorId = :editorId"),
    @NamedQuery(name = "Editor.findByEditorName", query = "SELECT e FROM Editor e WHERE e.editorName LIKE :editorName"),
    @NamedQuery(name = "Editor.findByEditorSummary", query = "SELECT e FROM Editor e WHERE e.editorSummary = :editorSummary")})
public class Editor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "editor_id")
    private Integer editorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "editor_name")
    private String editorName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "editor_summary")
    private String editorSummary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editorEditorId")
    private Collection<Book> bookCollection;

    public Editor() {
    }

    public Editor(Integer editorId) {
        this.editorId = editorId;
    }

    public Editor(Integer editorId, String editorName, String editorSummary) {
        this.editorId = editorId;
        this.editorName = editorName;
        this.editorSummary = editorSummary;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getEditorSummary() {
        return editorSummary;
    }

    public void setEditorSummary(String editorSummary) {
        this.editorSummary = editorSummary;
    }

    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (editorId != null ? editorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editor)) {
            return false;
        }
        Editor other = (Editor) object;
        if ((this.editorId == null && other.editorId != null) || (this.editorId != null && !this.editorId.equals(other.editorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.booxtore.entity.Editor[ editorId=" + editorId + " ]";
    }
    
}
