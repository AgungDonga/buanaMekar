/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BWP
 */
@Entity
@Table(name = "penagihan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Penagihan.findAll", query = "SELECT p FROM Penagihan p")
    , @NamedQuery(name = "Penagihan.findById", query = "SELECT p FROM Penagihan p WHERE p.id = :id")
    , @NamedQuery(name = "Penagihan.findByStatusPenagihan", query = "SELECT p FROM Penagihan p WHERE p.statusPenagihan = :statusPenagihan")
    , @NamedQuery(name = "Penagihan.findByCatatan", query = "SELECT p FROM Penagihan p WHERE p.catatan = :catatan")})
public class Penagihan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status_penagihan")
    private String statusPenagihan;
    @Column(name = "catatan")
    private String catatan;
    @JoinColumn(name = "orderan", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orderan orderan;

    public Penagihan() {
    }

    public Penagihan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusPenagihan() {
        return statusPenagihan;
    }

    public void setStatusPenagihan(String statusPenagihan) {
        this.statusPenagihan = statusPenagihan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Orderan getOrderan() {
        return orderan;
    }

    public void setOrderan(Orderan orderan) {
        this.orderan = orderan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penagihan)) {
            return false;
        }
        Penagihan other = (Penagihan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Penagihan[ id=" + id + " ]";
    }
    
}
