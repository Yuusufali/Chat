package com.test.chat.services;

import com.test.chat.models.Message;
import com.test.chat.models.RequestMessage;
import com.test.chat.modelsDto.ChatRoomDto;
import com.test.chat.modelsDto.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto sendMessage(String chatTopic, RequestMessage message);

    List<MessageDto> getChatMessages(Long chatId);
}
