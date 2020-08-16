/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.entities.Produk;
import com.example.buanaMekar.services.OrderanService;
import com.example.buanaMekar.services.SuratJalanService;
import com.example.buanaMekar.services.TokoService;
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
public class OrderanController {
    
    @Autowired
    OrderanService service;
    
    @Autowired
    TokoService tokoService;
    
    @Autowired
    SuratJalanService suratJalanService;
    
    @RequestMapping("/orderan/createOrderan")
    public String createOrderan(){
        return "createOrderan";
    }

    @RequestMapping("/orderan")
    public String viewOrderanPage(Model model){
        List<Orderan> listOrderans = service.listAll();
        model.addAttribute("produks", service.getAllProduk());
        model.addAttribute("tokos", service.getAllToko());
        if(listOrderans.size() >0){
            if(listOrderans.get(0).getStatus().equals("0")){
                model.addAttribute("listOrderans",listOrderans);
            }
        }
        return "listOrderan";
    }
    
    @RequestMapping("/orderan/suratJalan")
    public String cetakSuratJalan(Model model){
        List<Orderan> listOrderans = service.listAll();
        Orderan orderan = new Orderan();
        
        for (int i = 0; i < listOrderans.size(); i++) {
            orderan.setId(listOrderans.get(i).getId());
            orderan.setStatus("1");
            orderan.setProduk(listOrderans.get(i).getProduk());
            orderan.setQuantity(listOrderans.get(i).getQuantity());
            orderan.setToko(listOrderans.get(i).getToko());
            orderan.setTotalHarga(listOrderans.get(i).getTotalHarga());
            SuratJalan sj = new SuratJalan();
            sj.setOrderan(orderan);
            service.save(orderan);
            suratJalanService.save(sj);
        }
        return "listProduk";

    }
    
    @RequestMapping("/orderan/new")
    public String showNewOrderanForm(Model model) {
        Orderan orderan = new Orderan();
        model.addAttribute("produk", service.getAllProduk());
        model.addAttribute("toko", service.getAllToko());
        model.addAttribute("orderan", orderan);
        return "createOrderan";
    }

    @RequestMapping(value = "/orderan/save",method = RequestMethod.POST)
    public String saveOrderan(@ModelAttribute("orderan")Orderan orderan){
        orderan.setStatus("0"); // set default Status to = 0
        orderan.setTotalHarga(orderan.getQuantity() * orderan.getProduk().getHarga());
        service.save(orderan);
        return "redirect:/orderan";
    }
    
    @RequestMapping("/orderan/edit/{id}")
    public ModelAndView showEditOrderanForm(@PathVariable(name = "id")Integer id, Model model){
        ModelAndView mav = new ModelAndView("editOrderan");
        Orderan orderan = service.get(id);
        model.addAttribute("produk", service.getAllProduk());
        model.addAttribute("toko", service.getAllToko());
        mav.addObject("orderan",orderan);
        return mav;
    }
    
    @RequestMapping("/orderan/delete/{id}")
    public String deleteOrderan(@PathVariable(name = "id")Integer id){
        service.delete(id);
        
        return "redirect:/orderan";
    }
    
}
