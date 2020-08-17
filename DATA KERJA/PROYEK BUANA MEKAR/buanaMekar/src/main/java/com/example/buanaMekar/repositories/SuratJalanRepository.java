/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;

import com.example.buanaMekar.entities.SuratJalan;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface SuratJalanRepository extends CrudRepository<SuratJalan, Integer>{
    
    List<SuratJalan> findAll();
    
    @Query("SELECT sj, o FROM SuratJalan sj, Orderan o WHERE sj.status = 0 AND o.status = 1 Group by o.toko, sj.tglKirim")
    List<SuratJalan> findAllSuratJalan();
}
