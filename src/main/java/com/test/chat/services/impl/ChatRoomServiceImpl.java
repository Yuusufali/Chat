package com.test.chat.services.impl;

import com.test.chat.mappers.ChatRoomMapper;
import com.test.chat.models.*;
import com.test.chat.modelsDto.ChatRoomDto;
import com.test.chat.repositories.ChatRoomRepository;
import com.test.chat.repositories.UsersRepository;
import com.test.chat.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UsersRepository usersRepository;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    public List<ChatRoomDto> getChatRoomsByUser(Long userId) {
        return chatRoomMapper.mapToChatRoomDtoList(chatRoomRepository.findAllByUser(userId));
    }
    @Override
    public ChatRoomDto getChatRoom(String memberEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String chatTopic = Stream.of(authentication.getName(), memberEmail)
                .sorted().collect(Collectors.joining("_"));
        ChatRoom chatRoom = chatRoomRepository.findAllByChatTopicEquals(chatTopic);
        if(chatRoom==null){
            chatRoom = createPersonalChat(memberEmail);
        }
        chatRoom.setCreator(usersRepository.findAllByEmail(authentication.getName()));
        return chatRoomMapper.mapToChatRoomDto(chatRoom);
    }

    @Override
    public ChatRoomDto createGroupChatRoom(ChatRoomRequest chatRoomReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users requester = usersRepository.findAllByEmail(authentication.getName());
        List<Users> members = (chatRoomReq.getUsers()==null)? new ArrayList<>() : chatRoomReq.getUsers();
        String chatTopic = requester.getEmail() + chatRoomReq.getChatName().replaceAll("//s","") + System.currentTimeMillis();
        members.add(requester);
        ChatRoom chatRoom = ChatRoom.builder()
                .chatName(chatRoomReq.getChatName())
                .chatTopic(chatTopic)
                .creator(requester)
                .chatType(ChatRoom.ChatType.GROUP)
                .users(members)
                .build();
        return chatRoomMapper.mapToChatRoomDto(chatRoomRepository.save(chatRoom));
    }

    @Override
    public ChatRoom createPersonalChat(String memberEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users requester = usersRepository.findAllByEmail(authentication.getName());
        Users receiver = usersRepository.findAllByEmail(memberEmail);
        if(requester==null || receiver==null){
            return null;
        }
        if(!requester.getEmail().equals(authentication.getName())){
            return null;
        }
        String chatTopic = Stream.of(authentication.getName(), memberEmail)
                .sorted().collect(Collectors.joining("_"));
        ChatRoom chatRoom = ChatRoom.builder()
                .chatTopic(chatTopic)
                .creator(requester)
                .chatType(ChatRoom.ChatType.PERSONAL)
                .users(List.of(requester, receiver))
                .build();
        return chatRoomRepository.save(chatRoom);
    }


    @Override
    public ChatRoomDto updateGroupChatRoom(ChatRoom chatRoom) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName().equals(chatRoom.getCreator().getEmail())) {
            ChatRoom chatRoomUpdated = chatRoomRepository.findAllById(chatRoom.getId());
            chatRoomUpdated.setChatName(chatRoom.getChatName());
            chatRoomUpdated.setUsers(chatRoom.getUsers());
            return chatRoomMapper.mapToChatRoomDto(chatRoomRepository.save(chatRoomUpdated));
        }
        return null;
    }

    @Override
    public boolean getOutOfChat(Long chatId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ChatRoom chatRoom = chatRoomRepository.findAllById(chatId);
        if(chatRoom!=null && authentication!=null){
            String email = authentication.getName();
            List<Users> users = chatRoom.getUsers().stream()
                    .filter(user -> !user.getEmail().equals(email))
                    .toList();
            if(users.isEmpty()){
                chatRoomRepository.deleteById(chatId);
            }else {
                if (chatRoom.getCreator().getEmail().equals(email)) {
                    chatRoom.setCreator(users.get(0));
                }
                chatRoom.setUsers(users);
                chatRoomRepository.save(chatRoom);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGroupChatRoom(Long chatId) {
        ChatRoom chatRoom = chatRoomRepository.findAllById(chatId);
        if(chatRoom!=null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(chatRoom.getCreator().getEmail().equals(authentication.getName())){
                chatRoomRepository.deleteById(chatId);
                return true;
            }
        }
        return false;
    }
}
