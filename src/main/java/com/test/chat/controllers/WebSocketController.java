package com.test.chat.controllers;

import com.test.chat.models.RequestMessage;
import com.test.chat.modelsDto.MessageDto;
import com.test.chat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class WebSocketController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat/{chatTopic}")  // Эта аннотация говорит, что данный метод будет вызываться при получении сообщения по адресу "/chat/{chatTopic}"
    @SendTo("/topic/messages/{chatTopic}") //  Эта аннотация указывает, что результат выполнения данного метода будет отправлен всем подписчикам на тему "/topic/messages/{chatTopic}"
    public MessageDto processMessage(@DestinationVariable("chatTopic") String chatTopic,
                                     @RequestBody RequestMessage message){
        System.out.println(chatTopic);
        return messageService.sendMessage(chatTopic, message);
    }
}
