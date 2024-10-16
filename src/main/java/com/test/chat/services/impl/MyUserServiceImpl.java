package com.test.chat.services.impl;

import com.test.chat.mappers.UsersMapper;
import com.test.chat.models.Users;
import com.test.chat.modelsDto.UsersDto;
import com.test.chat.repositories.UsersRepository;
import com.test.chat.services.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserServiceImpl implements MyUserService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        return usersMapper.mapToUserDtoList(usersList);
    }

    @Override
    public UsersDto searchUser(String userEmail) {
        if(userEmail!=null){
            Users user = usersRepository.findAllByEmail(userEmail.trim());
            return usersMapper.mapToUserDto(user);
        }
        return null;
    }
}
