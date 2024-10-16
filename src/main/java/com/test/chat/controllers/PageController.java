package com.test.chat.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;

@Controller
@RequestMapping(value = "/home")
public class PageController {

    @GetMapping(value = "/login")
    public String getLoginPage(Model model){
        return "Home";
    }

    @GetMapping(value = "/chat")
    public String getChatPage(){
        System.out.println(" gdg");
        return "Chat";
    }
}
