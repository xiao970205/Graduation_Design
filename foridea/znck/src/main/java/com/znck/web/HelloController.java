package com.znck.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.znck.service.RunService;

@Controller
public class HelloController {
    
    @Autowired
    private RunService runService;
    
    @RequestMapping("/hello")
    public String hello(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
    
    @RequestMapping("/saveCar2")
    public void saveCar(Model model,
        @RequestParam(value = "name", required = false, defaultValue = "World") String name){
        System.out.println(model);
    }
}
