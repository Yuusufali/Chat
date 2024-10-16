package com.test.chat.services.impl;

import com.test.chat.mappers.MessageMapper;
import com.test.chat.models.ChatRoom;
import com.test.chat.models.Message;
import com.test.chat.models.RequestMessage;
import com.test.chat.modelsDto.ChatRoomDto;
import com.test.chat.modelsDto.MessageDto;
import com.test.chat.repositories.ChatRoomRepository;
import com.test.chat.repositories.MessageRepository;
import com.test.chat.repositories.UsersRepository;
import com.test.chat.services.ChatRoomService;
import com.test.chat.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageMapper messageMapper;
    private final ChatRoomService chatRoomService;
    private final UsersRepository usersRepository;

    @Override
    public MessageDto sendMessage(String chatTopic, RequestMessage message) {
        if(chatTopic==null){
            ChatRoomDto chatRoom = chatRoomService.getChatRoom(message.getReceiverEmail());
            chatTopic = chatRoom.getChatTopic();
        }
        ChatRoom chatRoom = chatRoomRepository.findAllByChatTopicEquals(chatTopic.trim());
        if(chatRoom!=null) {
            Message newMessage = Message.builder()
                    .sender(usersRepository.findAllByEmail(message.getSenderEmail()))
                    .messageText(message.getMessageText())
                    .chatRoom(chatRoom)
                    .build();
            return messageMapper.mapToMessageDto(messageRepository.save(newMessage));
        }
        return null;
    }

    @Override
    public List<MessageDto> getChatMessages(Long chatId) {
        List<Message> messageList = messageRepository.findAllByChatRoom_Id(chatId);
        return messageMapper.mapToMessageDtoList(messageList);
    }
}
