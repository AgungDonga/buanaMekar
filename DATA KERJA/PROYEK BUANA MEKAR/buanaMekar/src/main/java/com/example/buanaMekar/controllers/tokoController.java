/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Toko;
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
public class tokoController {
    
    @Autowired
    private TokoService service;
    
    
    @RequestMapping("/toko/createToko")
    public String createToko(){
        return "createToko";
    }

    @RequestMapping("/toko")
    public String viewToko(Model model){
        List<Toko> listTokos = service.listAll();
        model.addAttribute("listTokos",listTokos);
        return "listToko";

    }

    @RequestMapping("/toko/new")
    public String showNewTokoForm(Model model) {
        Toko toko = new Toko();
        model.addAttribute("toko", toko);
        return "createToko";
    }

    @RequestMapping(value = "/toko/save",method = RequestMethod.POST)
    public String saveToko(@ModelAttribute("toko")Toko toko){
        service.save(toko);
        return "redirect:/toko";
    }
    
    @RequestMapping("/toko/edit/{id}")
    public ModelAndView showEditTokoForm(@PathVariable(name = "id")Integer id){
        ModelAndView mav = new ModelAndView("editToko");
        Toko toko = service.get(id);
        mav.addObject("toko",toko);
        return mav;
    }
    
    @RequestMapping("/toko/delete/{id}")
    public String deleteToko(@PathVariable(name = "id")Integer id){
        service.delete(id);
        
        return "redirect:/toko";
    }
    
}
