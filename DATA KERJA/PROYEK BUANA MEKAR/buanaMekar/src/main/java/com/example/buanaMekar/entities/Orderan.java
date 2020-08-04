/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    , @NamedQuery(name = "Orderan.findByTglKirim", query = "SELECT o FROM Orderan o WHERE o.tglKirim = :tglKirim")
    , @NamedQuery(name = "Orderan.findByTglJatuhTempo", query = "SELECT o FROM Orderan o WHERE o.tglJatuhTempo = :tglJatuhTempo")
    , @NamedQuery(name = "Orderan.findByPpn", query = "SELECT o FROM Orderan o WHERE o.ppn = :ppn")
    , @NamedQuery(name = "Orderan.findByQuantity", query = "SELECT o FROM Orderan o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "Orderan.findByStatus", query = "SELECT o FROM Orderan o WHERE o.status = :status")})
public class Orderan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_harga")
    private Double totalHarga;
    @Column(name = "tgl_kirim")
    @Temporal(TemporalType.DATE)
    private Date tglKirim;
    @Column(name = "tgl_jatuh_tempo")
    @Temporal(TemporalType.DATE)
    private Date tglJatuhTempo;
    @Column(name = "ppn")
    private Double ppn;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "produk", referencedColumnName = "id")
    @ManyToOne
    private Produk produk;
    @JoinColumn(name = "toko", referencedColumnName = "id")
    @ManyToOne
    private Toko toko;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderan")
    private Collection<Penagihan> penagihanCollection;

    public Orderan() {
    }

    public Orderan(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Date getTglKirim() {
        return tglKirim;
    }

    public void setTglKirim(Date tglKirim) {
        this.tglKirim = tglKirim;
    }

    public Date getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(Date tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public Double getPpn() {
        return ppn;
    }

    public void setPpn(Double ppn) {
        this.ppn = ppn;
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
    public Collection<Penagihan> getPenagihanCollection() {
        return penagihanCollection;
    }

    public void setPenagihanCollection(Collection<Penagihan> penagihanCollection) {
        this.penagihanCollection = penagihanCollection;
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
