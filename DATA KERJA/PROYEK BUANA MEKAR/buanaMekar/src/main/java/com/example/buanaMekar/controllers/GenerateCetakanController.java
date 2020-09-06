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
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GenerateCetakanController {
    
    @Autowired
    InvoiceService service;
    

    @RequestMapping("/generateLaporan")
    public String viewInvoicePage(Model model){
        List<Invoice> listInvoices = service.listAllInvoice();
        model.addAttribute("listInvoices",listInvoices);
        return "generateLaporanBulanan";
    }
    
}
