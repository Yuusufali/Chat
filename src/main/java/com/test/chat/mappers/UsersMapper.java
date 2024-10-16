package com.test.chat.mappers;

import com.test.chat.models.Users;
import com.test.chat.modelsDto.UsersDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto mapToUserDto(Users user);
    List<UsersDto> mapToUserDtoList(List<Users> usersList);
}
