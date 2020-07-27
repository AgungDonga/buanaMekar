/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.User;
import com.example.buanaMekar.services.UserService;
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
public class mainController {
    
    @Autowired
    private UserService service;
    
    
    @RequestMapping("/createuser")
    public String createUser(){
        return "createUser";
    }

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "welcome";

    }
//    
//    @RequestMapping("/login")
//    public String viewLoginPage(){
//        return "login";
//
//    }

    @RequestMapping("/new")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user")User user){
        service.save(user);
        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name = "id")Long id){
        ModelAndView mav = new ModelAndView("editUser");
        User user = service.get(id);
        mav.addObject("user",user);
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id")Long id){
        service.delete(id);
        
        return "redirect:/";
    }
}
