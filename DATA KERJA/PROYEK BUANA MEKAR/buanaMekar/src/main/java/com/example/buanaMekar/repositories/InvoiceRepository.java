/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;

import com.example.buanaMekar.entities.Invoice;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String>{
    
    List<Invoice> findAll();
    
    @Query("SELECT i FROM Invoice i where i.status = 0 GROUP BY i.invoice ")
    List<Invoice> getAllInvoice();
}
