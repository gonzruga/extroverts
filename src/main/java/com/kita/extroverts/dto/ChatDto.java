package com.kita.extroverts.dto;

import com.kita.extroverts.model.Chat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ChatDto {

    private UUID id;

    private String title;

    private Date createdAt  = new Date();
    private Date updatedAt = null;

    public Chat dtoToChat(){
        final Chat chat = new Chat();

        chat.setId(id);
        chat.setTitle(title);

        return  chat;
    }
}
