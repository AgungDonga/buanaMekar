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
    
    @Query("SELECT sj, o FROM SuratJalan sj, Orderan o WHERE sj.status = 0 AND o.status = 1 AND o.id = sj.orderan Group by o.toko.namaToko, sj.tglKirim")
    List<SuratJalan> findAllSuratJalan();
    
    //@Query(value = "SELECT sj FROM SuratJalan WHERE id LIKE ?1 OR topic LIKE ?1 OR forum_description LIKE ?1 OR date_forum LIKE ?1")
    
    @Query("SELECT sj, o FROM SuratJalan sj, Orderan o WHERE sj.status = 0 AND sj.tglKirim= ?2 AND o.toko.namaToko = ?1 AND o.id = sj.orderan")
    List<SuratJalan> listDetailSuratJalan(String key1, String key2);
    
}
