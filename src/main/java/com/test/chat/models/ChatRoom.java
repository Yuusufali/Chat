package com.test.chat.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "chatRooms")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatName;
    @NonNull
    private String chatTopic;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users creator;
    public enum ChatType { PERSONAL, GROUP }
    @NonNull
    private ChatType chatType;
    
    @CreationTimestamp
    private Timestamp createTime;
    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Users> users;
}
