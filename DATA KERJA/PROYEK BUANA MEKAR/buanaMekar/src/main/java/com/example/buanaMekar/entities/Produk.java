/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Insane
 */
@Entity
public class Produk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String merk_produk;
    private String catatan;
    private Integer harga;
    private Integer stok;
    
    @JoinColumn(name = "jenis_produk", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JenisProduk jenis_produk;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerk_produk() {
        return merk_produk;
    }

    public void setMerk_produk(String merk_produk) {
        this.merk_produk = merk_produk;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
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
        this.catatan = catatan;
    }

    public JenisProduk getJenis_produk() {
        return jenis_produk;
    }

    public void setJenis_produk(JenisProduk jenis_produk) {
        this.jenis_produk = jenis_produk;
    }

    
    
    
    
}
