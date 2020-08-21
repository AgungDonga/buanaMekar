/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.repositories.InvoiceRepository;
import com.example.buanaMekar.repositories.OrderanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Insane
 */
@Service
public class InvoiceService {
    
    @Autowired
    InvoiceRepository repo;
    
    @Autowired
    OrderanRepository OrderanRepo;
    
    public List<Invoice> listAll() {
        return repo.findAll();

    }
    
    public List<Invoice> listAllInvoice() {
        return repo.getAllInvoice();

    }

    public void save(Invoice invoice) {
        repo.save(invoice);

    }

    public Invoice get(String string) {
        return repo.findById(string).get();

    }

    public void delete(String string) {
        repo.deleteById(string);

    }
    
    public List<Orderan> getAllOrderan() {
        return OrderanRepo.findAll();
    }
    
}
