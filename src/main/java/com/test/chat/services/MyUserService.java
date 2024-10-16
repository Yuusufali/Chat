package com.test.chat.services;

import com.test.chat.modelsDto.UsersDto;

import java.util.List;

public interface MyUserService {
    List<UsersDto> getAllUsers();

    UsersDto searchUser(String userEmail);
}
