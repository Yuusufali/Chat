package com.test.chat.modelsDto;
import com.test.chat.models.Users;
import lombok.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDto {
    private Long id;
    private UsersDto sender;
    private String messageText;
    private Timestamp messageDate;
    private ChatRoomDto chatRoom;
}
