/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;
import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.repositories.OrderanRepository;
import com.example.buanaMekar.repositories.SuratJalanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Insane
 */
@Service
public class SuratJalanService {
    
    @Autowired
    SuratJalanRepository repo;
    
    @Autowired
    OrderanRepository orderanRepo;
    
    public List<SuratJalan> listAll() {
        return repo.findAll();

    }

    public void save(SuratJalan sj) {
        repo.save(sj);

    }

    public SuratJalan get(Integer integer) {
        return repo.findById(integer).get();

    }

    public void delete(Integer integer) {
        repo.deleteById(integer);

    }
    
//    public List<Orderan> getAllOrderan() {
//        return orderanRepo.findAll();
//    }
    
}
