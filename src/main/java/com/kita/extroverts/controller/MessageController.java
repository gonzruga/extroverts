package com.kita.extroverts.controller;


import com.kita.extroverts.dto.ChatDto;
import com.kita.extroverts.dto.MessageDto;
import com.kita.extroverts.model.Message;
import com.kita.extroverts.service.ChatService;
import com.kita.extroverts.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private final MessageService service;

    @Autowired
    private final ChatService chatService;

    // CREATE - POST
    @GetMapping("/messageForm/{chatId}")
    public String messageForm(@PathVariable UUID chatId, Model model) {
        MessageDto message = new MessageDto();
        model.addAttribute("message", message);
        model.addAttribute("chatId", chatId);
        return "message-form";
    }

    @PostMapping("/messageSubmit")
    public String messageSubmit(@ModelAttribute MessageDto message, @RequestParam UUID chatId, Model model) {

        message.setChatRoom(chatService.getChatById(chatId));  // Assign Host Chat
        message.setChatId(chatId);  // Assign chatId

        service.saveMessage(message);

        model.addAttribute("message", message);
        model.addAttribute("chatId", chatId);

        return "message-submit";
    }

//    public String messageSubmit(@ModelAttribute MessageDto message, Model model, @RequestParam UUID chatId) {


        // READ - GET
    @GetMapping("/messagePage/{id}")
    public String messagePage(@PathVariable UUID id, Model model) {
//        model.addAttribute("message", service.getmessageById(id));
        Message message = service.getMessageById(id);
        model.addAttribute("message", message);
        return "message-page";
    }

    @GetMapping("/messageList")
    public String findAllmessages(Model model) {
//        model.addAttribute("messages", service.listAll());
        List<Message> messages = service.listAll();
        model.addAttribute("message", messages);
        return "message-list";
    }

    // UPDATE - PUT
    @PostMapping("/messageUpdate/{id}")
    public String updateMessage(@ModelAttribute MessageDto message, @PathVariable UUID id){
        service.updateMessage(message);
        return "redirect:/messagePage/{id}";
    }

    // DELETE
    @GetMapping("/messageDelete/{id}")
    public String deleteMessage(@PathVariable UUID id) {
        service.deleteMessage(id);
        return "redirect:/about";
    }

}
