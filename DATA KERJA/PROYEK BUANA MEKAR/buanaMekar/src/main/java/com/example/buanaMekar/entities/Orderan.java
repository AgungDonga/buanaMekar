/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BWP
 */
@Entity
@Table(name = "orderan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderan.findAll", query = "SELECT o FROM Orderan o")
    , @NamedQuery(name = "Orderan.findById", query = "SELECT o FROM Orderan o WHERE o.id = :id")
    , @NamedQuery(name = "Orderan.findByTotalHarga", query = "SELECT o FROM Orderan o WHERE o.totalHarga = :totalHarga")
    , @NamedQuery(name = "Orderan.findByQuantity", query = "SELECT o FROM Orderan o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "Orderan.findByStatus", query = "SELECT o FROM Orderan o WHERE o.status = :status")})
public class Orderan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_harga")
    private Double totalHarga;
    @Column(name = "quantity")
    private Integer quantity;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "produk", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Produk produk;
    @JoinColumn(name = "toko", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Toko toko;
    @OneToMany(mappedBy = "order1", fetch = FetchType.LAZY)
    private List<Invoice> invoiceList;

    public Orderan() {
    }

    public Orderan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Toko getToko() {
        return toko;
    }

    public void setToko(Toko toko) {
        this.toko = toko;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
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
        if (!(object instanceof Orderan)) {
            return false;
        }
        Orderan other = (Orderan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Orderan[ id=" + id + " ]";
    }
    
}
