/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Insane
 */
@Controller
public class mainController {
    
    @RequestMapping("/")
    public String viewHomePage(){
        return "welcome";
    }
    
    @RequestMapping("/createuser")
    public String createUser(){
        return "createUser";
    }
    
}
