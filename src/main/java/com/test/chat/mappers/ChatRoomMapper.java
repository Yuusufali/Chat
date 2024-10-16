package com.test.chat.mappers;

import com.test.chat.models.ChatRoom;
import com.test.chat.modelsDto.ChatRoomDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {
    ChatRoomDto mapToChatRoomDto(ChatRoom chatRoom);
    List<ChatRoomDto> mapToChatRoomDtoList(List<ChatRoom> chatRoomList);
}
