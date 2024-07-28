package com.kita.extroverts.dto;

import com.kita.extroverts.model.Chat;
import com.kita.extroverts.model.Message;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private UUID id;
    private String content;

    private UUID chatId;    //Only here, used in messageSubmit
    private Chat chatRoom;   //Used in MessageController: 'messageSubmit'

    private Date createdAt  = new Date();
    private Date updatedAt = null;

    public Message dtoToMessage() {
        final Message message = new Message();

        message.setId(id);
        message.setContent(content);

        message.setChatRoom(chatRoom);

        return message;

    }

}
