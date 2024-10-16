package com.test.chat.services;

import com.test.chat.models.ChatRoom;
import com.test.chat.models.ChatRoomRequest;
import com.test.chat.models.Message;
import com.test.chat.models.RequestMessage;
import com.test.chat.modelsDto.ChatRoomDto;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDto getChatRoom(String memberEmail);

    ChatRoomDto createGroupChatRoom(ChatRoomRequest chatRoom);

    ChatRoom createPersonalChat(String message);

    List<ChatRoomDto> getChatRoomsByUser(Long userId);

    ChatRoomDto updateGroupChatRoom(ChatRoom chatRoom);

    boolean deleteGroupChatRoom(Long chatId);

    boolean getOutOfChat(Long chatId);
}
