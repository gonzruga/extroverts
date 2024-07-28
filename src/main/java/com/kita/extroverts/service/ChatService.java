package com.kita.extroverts.service;

import com.kita.extroverts.dto.ChatDto;
import com.kita.extroverts.dto.MessageDto;
import com.kita.extroverts.model.Chat;
import com.kita.extroverts.model.Message;
import com.kita.extroverts.repository.ChatRepository;
import com.kita.extroverts.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;
    // CREATE
    public Chat saveChat(ChatDto chat) { return repository.save(chat.dtoToChat()); }

    public Chat getChatById(UUID id) { return repository.findById(id).orElse(null); }

    // READ - GET
    public List<Chat> listAll() { return repository.findAll(); }

    // DELETE
    public String deleteChat(UUID id) {repository.deleteById(id); return "Chat removed ID: " + id; }

    //UPDATE
    public Chat updateChat(ChatDto chat) {
        Chat existingChat = repository.findById(chat.getId()).orElse(null);
        existingChat.setTitle(chat.getTitle());
//        existingChat.setMessages(chat.dtoToChat().getMessages());
        existingChat.setUpdatedAt(new Date());
        return repository.save(existingChat);
    }

}
