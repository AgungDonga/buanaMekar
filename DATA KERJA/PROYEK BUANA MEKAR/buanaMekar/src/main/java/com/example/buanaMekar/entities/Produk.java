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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BWP
 */
@Entity
@Table(name = "produk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produk.findAll", query = "SELECT p FROM Produk p")
    , @NamedQuery(name = "Produk.findById", query = "SELECT p FROM Produk p WHERE p.id = :id")
    , @NamedQuery(name = "Produk.findByMerkProduk", query = "SELECT p FROM Produk p WHERE p.merkProduk = :merkProduk")
    , @NamedQuery(name = "Produk.findByHarga", query = "SELECT p FROM Produk p WHERE p.harga = :harga")
    , @NamedQuery(name = "Produk.findByStok", query = "SELECT p FROM Produk p WHERE p.stok = :stok")
    , @NamedQuery(name = "Produk.findByCatatan", query = "SELECT p FROM Produk p WHERE p.catatan = :catatan")})
public class Produk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "merk_produk")
    private String merkProduk;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "stok")
    private Integer stok;
    @Column(name = "catatan")
    private String catatan;
    @JoinColumn(name = "jenis_produk", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JenisProduk jenisProduk;
    @OneToMany(mappedBy = "produk", fetch = FetchType.LAZY)
    private List<Orderan> orderanList;

    public Produk() {
    }

    public Produk(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerkProduk() {
        return merkProduk;
    }

    public void setMerkProduk(String merkProduk) {
        this.merkProduk = merkProduk.toUpperCase();
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan.toUpperCase();
    }

    public JenisProduk getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(JenisProduk jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    @XmlTransient
    public List<Orderan> getOrderanList() {
        return orderanList;
    }

    public void setOrderanList(List<Orderan> orderanList) {
        this.orderanList = orderanList;
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
        if (!(object instanceof Produk)) {
            return false;
        }
        Produk other = (Produk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Produk[ id=" + id + " ]";
    }
    
}
