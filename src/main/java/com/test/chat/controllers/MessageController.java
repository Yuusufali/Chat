package com.test.chat.controllers;

import com.test.chat.modelsDto.MessageDto;
import com.test.chat.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/chat-message")
@RequiredArgsConstructor

public class MessageController {

    private final MessageService messageService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{chatId}")
    public List<MessageDto> getChatMessages(
            @PathVariable(value = "chatId") Long chatId){
        return messageService.getChatMessages(chatId);
    }
}
