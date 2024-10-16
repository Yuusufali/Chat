package com.test.chat.controllers;

import com.test.chat.models.ChatRoom;
import com.test.chat.models.ChatRoomRequest;
import com.test.chat.modelsDto.ChatRoomDto;
import com.test.chat.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chat-room")
@RequiredArgsConstructor

public class ChatRoomController {

    private final ChatRoomService chatRoomService;

//    Получение личного чата с определенным юзеров
//    Но кажется это лишняя, потому что у меня уже есть контроллер для получения всех чатов
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/personal/{member-email}")
    public ChatRoomDto getChatRoom(@PathVariable("member-email") String memberEmail){
        return chatRoomService.getChatRoom(memberEmail);
    }

//    Получение всех чатов определенного юзера
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{userId}")
    public List<ChatRoomDto> getChatRoomsByUser(
            @PathVariable(value = "userId",required = false) Long userId){
        return chatRoomService.getChatRoomsByUser(userId);
    }

//    Создание нового группового чата
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create-group")
    public ChatRoomDto createChatRoom(@RequestBody ChatRoomRequest chatRoom){
        return chatRoomService.createGroupChatRoom(chatRoom);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ChatRoomDto updateChatRoom(@RequestBody ChatRoom chatRoom){
        return chatRoomService.updateGroupChatRoom(chatRoom);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/get-out/{chatId}")
    public boolean getOutOfChat(
            @PathVariable(value = "chatId",required = false) Long chatId ){
        return chatRoomService.getOutOfChat(chatId);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{chatId}")
    public boolean deleteGroupChatRoom(
            @PathVariable(value = "chatId",required = false) Long chatId){
        return chatRoomService.deleteGroupChatRoom(chatId);
    }
}
