/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.services.InvoiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Insane
 */
@Controller
public class InvoiceController {
    
    @Autowired
    InvoiceService service;
    
    @RequestMapping("/invoice/createInvoice")
    public String createInvoice(){
        return "createInvoice";
    }

    @RequestMapping("/invoice")
    public String viewInvoicePage(Model model){
        List<Invoice> listInvoices = service.listAll();
        model.addAttribute("listInvoices",listInvoices);
        return "listInvoice";

    }

    @RequestMapping("/invoice/new")
    public String showNewInvoiceForm(Model model) {
        Invoice invoice = new Invoice();
        model.addAttribute("orderan", service.getAllOrderan());
        model.addAttribute("invoice", invoice);
        return "createInvoice";
    }

    @RequestMapping(value = "/invoice/save",method = RequestMethod.POST)
    public String saveInvoice(@ModelAttribute("invoice")Invoice invoice){
        service.save(invoice);
        return "redirect:/invoice";
    }
    
    @RequestMapping("/invoice/edit/{id}")
    public ModelAndView showEditInvoiceForm(@PathVariable(name = "id")String id, Model model){
        ModelAndView mav = new ModelAndView("editOrderan");
        Invoice invoice = service.get(id);
        model.addAttribute("orderan", service.getAllOrderan());
        mav.addObject("invoice",invoice);
        return mav;
    }
    
    @RequestMapping("/invoice/delete/{id}")
    public String deleteOrderan(@PathVariable(name = "id")String id){
        service.delete(id);
        
        return "redirect:/invoice";
    }
    
}
