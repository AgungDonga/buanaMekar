/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Produk;
import com.example.buanaMekar.services.ProdukService;
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
public class produkController {
    
    @Autowired
    private ProdukService service;
    
    
    @RequestMapping("/produk/createProduk")
    public String createProduk(){
        return "createProduk";
    }

    @RequestMapping("/produk")
    public String viewJenisProdukPage(Model model){
        List<Produk> listProduks = service.listAll();
        model.addAttribute("listProduks",listProduks);
        return "listProduk";

    }

    @RequestMapping("/produk/new")
    public String showNewProdukForm(Model model) {
        Produk produk = new Produk();
        model.addAttribute("jenisProduk", service.getAllJenisProduk());
        model.addAttribute("produk", produk);
        return "createProduk";
    }

    @RequestMapping(value = "/produk/save",method = RequestMethod.POST)
    public String saveProduk(@ModelAttribute("produk")Produk produk){
        service.save(produk);
        return "redirect:/produk";
    }
    
    @RequestMapping("/produk/edit/{id}")
    public ModelAndView showEditProdukForm(@PathVariable(name = "id")Long id, Model model){
        ModelAndView mav = new ModelAndView("editProduk");
        Produk produk = service.get(id);
        model.addAttribute("jenisProduk", service.getAllJenisProduk());
        mav.addObject("produk",produk);
        return mav;
    }
    
    @RequestMapping("/produk/delete/{id}")
    public String deleteProduk(@PathVariable(name = "id")Long id){
        service.delete(id);
        
        return "redirect:/produk";
    }
    
}
