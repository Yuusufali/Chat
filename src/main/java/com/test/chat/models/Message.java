package com.test.chat.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Users sender;
    @NonNull
    private String messageText;
    @CreationTimestamp
    private Timestamp messageDate;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ChatRoom chatRoom;
}
