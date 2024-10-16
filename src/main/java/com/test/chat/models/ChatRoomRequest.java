package com.test.chat.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ChatRoomRequest {
    private String chatName;
    private Users creator;
    List<Users> users;
}
