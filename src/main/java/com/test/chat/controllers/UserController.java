package com.test.chat.controllers;

import com.test.chat.modelsDto.UsersDto;
import com.test.chat.services.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor

public class UserController {

    private final MyUserService userService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/all")
    public List<UsersDto> getAllUser(){

        return userService.getAllUsers();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/search")
    public UsersDto searchUser(@RequestParam(name = "userEmail",required = false) String userEmail){
        return userService.searchUser(userEmail);
    }
}
