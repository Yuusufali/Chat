package com.test.chat.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestMessage {
    private String messageText;
    private String senderEmail;
    private String receiverEmail;
}
