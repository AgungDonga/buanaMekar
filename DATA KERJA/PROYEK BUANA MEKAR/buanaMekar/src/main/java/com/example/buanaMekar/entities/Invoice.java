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
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
    , @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id")
    , @NamedQuery(name = "Invoice.findByPpn", query = "SELECT i FROM Invoice i WHERE i.ppn = :ppn")
    , @NamedQuery(name = "Invoice.findByTglKirim", query = "SELECT i FROM Invoice i WHERE i.tglKirim = :tglKirim")
    , @NamedQuery(name = "Invoice.findByTglTerima", query = "SELECT i FROM Invoice i WHERE i.tglTerima = :tglTerima")
    , @NamedQuery(name = "Invoice.findByTglJatuhTempo", query = "SELECT i FROM Invoice i WHERE i.tglJatuhTempo = :tglJatuhTempo")
    , @NamedQuery(name = "Invoice.findByStatus", query = "SELECT i FROM Invoice i WHERE i.status = :status")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "ppn")
    private Integer ppn;
    @Column(name = "tgl_kirim")
    private String tglKirim;
    @Column(name = "tgl_terima")
    private String tglTerima;
    @Column(name = "tgl_jatuh_tempo")
    private String tglJatuhTempo;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "order", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Orderan order1;
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<Penagihan> penagihanList;

    public Invoice() {
    }

    public Invoice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPpn() {
        return ppn;
    }

    public void setPpn(Integer ppn) {
        this.ppn = ppn;
    }

    public String getTglKirim() {
        return tglKirim;
    }

    public void setTglKirim(String tglKirim) {
        this.tglKirim = tglKirim;
    }

    public String getTglTerima() {
        return tglTerima;
    }

    public void setTglTerima(String tglTerima) {
        this.tglTerima = tglTerima;
    }

    public String getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(String tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Orderan getOrder1() {
        return order1;
    }

    public void setOrder1(Orderan order1) {
        this.order1 = order1;
    }

    @XmlTransient
    public List<Penagihan> getPenagihanList() {
        return penagihanList;
    }

    public void setPenagihanList(List<Penagihan> penagihanList) {
        this.penagihanList = penagihanList;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Invoice[ id=" + id + " ]";
    }
    
}
