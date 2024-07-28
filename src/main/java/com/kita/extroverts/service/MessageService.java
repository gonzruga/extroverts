package com.kita.extroverts.service;

import com.kita.extroverts.dto.ChatDto;
import com.kita.extroverts.dto.MessageDto;
import com.kita.extroverts.model.Chat;
import com.kita.extroverts.model.Message;
import com.kita.extroverts.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Autowired
    private final MessageRepository repository;

    @Autowired
    private final ChatService chatService;

    // CREATE
    public Message saveMessage(MessageDto messageDto) {
        Message message = messageDto.dtoToMessage();
        message.setChatRoom(chatService.getChatById(messageDto.getChatId())); // Set chatRoom
        return repository.save(message);
    }
    // READ
    public Message getMessageById(UUID id) { return repository.findById(id).orElse(null); }

    public List<Message> listAll() { return repository.findAll(); }
    // DELETE
    public String deleteMessage(UUID id) {repository.deleteById(id); return "Message removed ID: " + id; }

    //UPDATE
    public Message updateMessage(MessageDto messsage) {
        Message existingMessage = repository.findById(messsage.getId()).orElse(null);
        existingMessage.setContent(messsage.getContent());
        existingMessage.setUpdatedAt(new Date());
        return repository.save(existingMessage);
    }
}
