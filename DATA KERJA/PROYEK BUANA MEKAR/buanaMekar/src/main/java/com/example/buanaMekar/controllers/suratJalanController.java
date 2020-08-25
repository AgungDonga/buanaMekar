/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.services.SuratJalanService;
import java.io.FileNotFoundException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Insane
 */
@Controller
public class suratJalanController {

    @Autowired
    SuratJalanService service;
    
    @RequestMapping("/suratJalan/report/{format}")
    public String generateReport(@PathVariable String format)throws FileNotFoundException, JRException{
        return service.exportReport(format);
    }

    @RequestMapping("/suratJalan")
    public String viewSuratJalanPage(Model model) {
        List<SuratJalan> listSuratJalans = service.listAllSuratJalan();
        model.addAttribute("listSuratJalans",listSuratJalans);
        return "listSuratJalan";
    }
    
    @RequestMapping("/detailSuratJalan")
    public String viewDetailSuratJalanPage(Model model, HttpServletRequest request) {
        List<SuratJalan> listSuratJalans = service.listDetailSuratJalan(request.getParameter("id").replaceAll("%20", " "), request.getParameter("id2").replaceAll("%20", " "));
        model.addAttribute("listSuratJalans",listSuratJalans);
        return "listDetailSuratJalan";
    }

}
