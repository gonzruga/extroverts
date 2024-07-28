package com.kita.extroverts.controller;

import com.kita.extroverts.dto.ChatDto;
import com.kita.extroverts.model.Chat;
import com.kita.extroverts.model.User;
import com.kita.extroverts.service.ChatService;
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
public class ChatController {

    @Autowired
    private final ChatService service;

    // CREATE - POST
    @GetMapping("/chatForm")
    public String chatForm(Model model) {
        ChatDto chat = new ChatDto();
        model.addAttribute("chat", chat);
        return "chat-form";
    }

    @PostMapping("/chatSubmit")
    public String chatSubmit(@ModelAttribute ChatDto chat, Model model) {
        model.addAttribute("chat", chat);
        service.saveChat(chat);
        return "chat-submit";
    }

    // READ - GET
    @GetMapping("/chatList")
    public String findAllChats(Model model) {
//        model.addAttribute("chats", service.listAll());
        List<Chat> chats = service.listAll();
        model.addAttribute("chats", chats);
        return "chat-list";
    }

    @GetMapping("/chatPage/{chatId}")
    public String chatPage(@PathVariable UUID chatId, Model model) {
//        model.addAttribute("chat", service.getChatById(chatId));
        Chat chat = service.getChatById(chatId);
        model.addAttribute("chat", chat);
        return "chat-page";
    }

    @GetMapping("/chatSearch")
    public String chatSearch(Model model){
        model.addAttribute("chat", new Chat());
        return "chat-page-search";
    }

    @PostMapping("/chatSearched")
    public String searchChat(@RequestParam UUID id) {
        return "redirect:/chatPage/" + id;
    }

    // UPDATE - PUT
    @PostMapping("/chatUpdate/{id}")
    public String updateChat(@ModelAttribute ChatDto chat, @PathVariable UUID id){
        service.updateChat(chat);
        return "redirect:/chatPage/{id}";
    }

    // DELETE
    @GetMapping("/chatDelete/{id}")
    public String deleteChat(@PathVariable UUID id) {
        service.deleteChat(id);
        return "redirect:/about";
    }

}
