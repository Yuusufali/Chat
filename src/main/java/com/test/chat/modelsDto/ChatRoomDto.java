package com.test.chat.modelsDto;
import com.test.chat.models.Users;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatRoomDto {
    private Long id;
    private String chatName;
    public enum ChatType { PERSONAL, GROUP }
    private ChatType chatType;
    private String chatTopic;
    private UsersDto creator;
    private List<UsersDto> users;
}