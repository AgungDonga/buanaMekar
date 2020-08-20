/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.services.InvoiceService;
import com.example.buanaMekar.services.SuratJalanService;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
    
    @Autowired
    SuratJalanService serviceSuratJalan;
    
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
    public String saveInvoice(HttpServletRequest request){
        Invoice invoicenya = new Invoice();
        String arrayBulan[]={"O","I","II","III", "IV", "V","VI","VII","VIII","IX","X", "XI","XII"};
        Double totalHarga= Double.MAX_VALUE;
        List<SuratJalan> listSuratJalans = serviceSuratJalan.listDetailSuratJalan(request.getParameter("id").replaceAll("%20", " "), request.getParameter("id2").replaceAll("%20", " "));
        for (int i = 0; i < listSuratJalans.size(); i++) {
            totalHarga = totalHarga + Integer.valueOf(listSuratJalans.get(i).getOrderan().getTotalHarga());
        }
        for (int i = 0; i < listSuratJalans.size(); i++) {
            invoicenya.setInvoice("/INV/BPK"+arrayBulan[Integer.valueOf(listSuratJalans.get(i).getTglKirim().substring(3, 4).replaceAll("0", ""))]+2020);
            System.out.println("Bulan ke "+Integer.valueOf(listSuratJalans.get(i).getTglKirim().substring(3, 4).replaceAll("0", "")) +"- "+ listSuratJalans.get(i).getTglKirim().substring(3, 4));
            //invoice.setPpn(Integer.tlistSuratJalans.get(i).getOrderan().getTotalHarga());
            invoicenya.setStatus(0);
            invoicenya.setSuratJalan(listSuratJalans.get(i));
            
            Calendar calTambah = Calendar.getInstance();

            calTambah.setTime(Date.valueOf(listSuratJalans.get(i).getTglKirim()));
            calTambah.add(Calendar.DAY_OF_MONTH, 30);
            invoicenya.setTglJatuhTempo(calTambah.getTime().toString());
            Double ppn =totalHarga * 0.1;
            invoicenya.setPpn(ppn.toString());
            service.save(invoicenya);
            
        }
        
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
