/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Penagihan;
import com.example.buanaMekar.services.PenagihanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Insane
 */
@Controller
public class penagihanController {

    @Autowired
    private PenagihanService service;

    
    @RequestMapping("/penagihan")
    public String viewJenisPenagihanPage(Model model) {
        List<Penagihan> listPenagihans = service.listAll();
        model.addAttribute("listPenagihans", listPenagihans);
        return "listPenagihan";

    }


}
