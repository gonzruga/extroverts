package com.kita.extroverts.controller;


import com.kita.extroverts.dto.HostRequestDto;
import com.kita.extroverts.model.Hobby;
import com.kita.extroverts.model.HostRequest;
import com.kita.extroverts.model.Nudge;
import com.kita.extroverts.model.User;
import com.kita.extroverts.service.HobbyService;
import com.kita.extroverts.service.HostRequestService;
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
public class HostRequestController {

    @Autowired
    private final HostRequestService service;

    @Autowired
    private final HobbyService hobbyService;

    // CREATE - POST
    @GetMapping("/hostRequestForm/{hobbyId}")
    public String HostRequestForm(@PathVariable Long hobbyId, Model model) {
        HostRequestDto hostRequest = new HostRequestDto();
        model.addAttribute("hostRequest", hostRequest);
        model.addAttribute("hobbyId", hobbyId);

        Hobby hobby = hobbyService.getHobbyById(hobbyId);
        if (hobby == null) {
            // Log an error or throw an exception if the hobby is not found
            throw new RuntimeException("Hobby not found for id: " + hobbyId);
        }
        model.addAttribute("hobby", hobby);

        return "host-request-form";
    }

    @PostMapping("/hostRequestSubmit")
    public String HostRequestSubmit(@ModelAttribute HostRequestDto hostRequest, @RequestParam Long hobbyId, Model model) {
        Hobby hobby = hobbyService.getHobbyById(hobbyId);
        if (hobby == null) {
            throw new RuntimeException("Hobby not found for id: " + hobbyId);
        }
//        if (hobbyId == null) {
//            throw new IllegalArgumentException("Hobby ID cannot be null");
//        }
        hostRequest.setHobby(hobby);  // Assign Hobby
        hostRequest.setHobbyId(hobbyId);  // Assign HobbyId

        service.saveHostRequest(hostRequest);

        model.addAttribute("hostRequest", hostRequest);
        model.addAttribute("hobbyId", hobbyId);

        return "host-request-submit";
    }

//    public String HostRequestSubmit(@ModelAttribute HostRequestDto HostRequest, Model model, @RequestParam UUID HostId) {


        // READ - GET
    @GetMapping("/hostRequestPage/{id}")
    public String HostRequestPage(@PathVariable UUID id, Model model) {
//        model.addAttribute("HostRequest", service.getHostRequestById(id));
        HostRequest hostRequest = service.getHostRequestById(id);
        if (hostRequest == null) {
            throw new RuntimeException("Host request not found for id: " + id);
        }
        model.addAttribute("hostRequest", hostRequest);
        return "host-request-page";
    }

    @GetMapping("/hostRequestList")
    public String findAllHostRequests(Model model) {
//        model.addAttribute("HostRequests", service.listAll());
        List<HostRequest> hostRequests = service.listAll();
        model.addAttribute("hostRequest", hostRequests);
        return "host-request-list";
    }

    // UPDATE - PUT
    @GetMapping("/hostRequestEdit/{id}")
    public String HostRequestEdit(@PathVariable UUID id, Model model) {
        HostRequest hostRequest = new HostRequest();
        hostRequest = service.getHostRequestById(id);
        model.addAttribute("hostRequest", hostRequest);
        return "host-request-edit";
    }

    @PostMapping("/hostRequestUpdate/{id}")
    public String updateHostRequest(@ModelAttribute HostRequestDto hostRequest, @PathVariable UUID id){
        service.updateHostRequest(hostRequest);
        return "redirect:/hostRequestPage/{id}";
    }

    // DELETE
    @GetMapping("/hostRequestDelete/{id}")
    public String deleteHostRequest(@PathVariable UUID id) {
        service.deleteHostRequest(id);
        return "redirect:/about";
    }

}
