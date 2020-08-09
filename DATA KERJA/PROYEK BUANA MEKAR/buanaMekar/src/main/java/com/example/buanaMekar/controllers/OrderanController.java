/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.services.OrderanService;
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
    
    @RequestMapping("/orderan/createOrderan")
    public String createOrderan(){
        return "createOrderan";
    }

    @RequestMapping("/orderan")
    public String viewOrderanPage(Model model){
        List<Orderan> listOrderans = service.listAll();
        model.addAttribute("listOrderans",listOrderans);
        return "listOrderan";

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
