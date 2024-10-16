package com.test.chat.mappers;

import com.test.chat.models.Message;
import com.test.chat.modelsDto.MessageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDto mapToMessageDto(Message message);
    List<MessageDto> mapToMessageDtoList(List<Message> messageList);
}
