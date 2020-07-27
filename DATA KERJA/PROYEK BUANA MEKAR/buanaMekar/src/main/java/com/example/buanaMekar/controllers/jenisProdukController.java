/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.JenisProduk;
import com.example.buanaMekar.services.JenisProdukService;
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
public class jenisProdukController {
    
    @Autowired
    private JenisProdukService service;
    
    
    @RequestMapping("/jenisProduk/createJenisProduk")
    public String createJenisProduk(){
        return "createJenisProduk";
    }

    @RequestMapping("/jenisProduk")
    public String viewJenisProdukPage(Model model){
        List<JenisProduk> listJenisProduks = service.listAll();
        model.addAttribute("listJenisProduks",listJenisProduks);
        return "listJenisProduk";

    }

    @RequestMapping("/jenisProduk/new")
    public String showNewJenisProdukForm(Model model) {
        JenisProduk jenisProduk = new JenisProduk();
        model.addAttribute("jenisProduk", jenisProduk);
        return "createJenisProduk";
    }

    @RequestMapping(value = "/jenisProduk/save",method = RequestMethod.POST)
    public String saveJenisProduk(@ModelAttribute("jenisproduk")JenisProduk jenisProduk){
        service.save(jenisProduk);
        return "redirect:/jenisProduk";
    }
    
    @RequestMapping("/jenisProduk/edit/{id}")
    public ModelAndView showEditJenisProdukForm(@PathVariable(name = "id")Long id){
        ModelAndView mav = new ModelAndView("editJenisProduk");
        JenisProduk jenisProduk = service.get(id);
        mav.addObject("jenisProduk",jenisProduk);
        return mav;
    }
    
    @RequestMapping("/jenisProduk/delete/{id}")
    public String deleteJenisProduk(@PathVariable(name = "id")Long id){
        service.delete(id);
        
        return "redirect:/jenisProduk";
    }
    
}
