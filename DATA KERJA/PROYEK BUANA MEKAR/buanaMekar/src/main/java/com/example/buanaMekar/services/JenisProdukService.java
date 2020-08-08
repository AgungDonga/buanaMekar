/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.JenisProduk;
import com.example.buanaMekar.repositories.JenisProdukRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Insane
 */
@Service
public class JenisProdukService {
    
    @Autowired
    JenisProdukRepository repo;

    public List<JenisProduk> listAll() {
        return repo.findAll();

    }

    public void save(JenisProduk jenisProduk) {
        repo.save(jenisProduk);

    }

    public JenisProduk get(Integer id) {
        return repo.findById(id).get();

    }

    public void delete(Integer id) {
        repo.deleteById(id);

    }
    
    public List<JenisProduk> getAll(){
        return repo.findAll();
    }
}
