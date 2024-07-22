package com.kita.extroverts.controller;

import com.kita.extroverts.dto.NudgeDto;
import com.kita.extroverts.model.Nudge;
import com.kita.extroverts.model.User;
import com.kita.extroverts.service.NudgeService;
import com.kita.extroverts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NudgeController {

    @Autowired
    private final NudgeService service;
    @Autowired
    private final UserService userService;

    // CREATE - POST

    @GetMapping("/nudgeForm/{hostId}")
    public String nudgeForm(
            @PathVariable(name="hostId") UUID hostId,
            Model model){
        Nudge nudge = new Nudge();
        model.addAttribute("hostId", hostId);
        model.addAttribute("nudge", nudge);

        User user = userService.getUserById(hostId);
        if (user == null) {
            // Log an error or throw an exception if the user is not found
            throw new RuntimeException("User not found for id: " + hostId);
        }
        model.addAttribute("user", user);

        return "nudge-form";
    }

    @PostMapping("/nudgeSubmit")
    public String saveNudge(@ModelAttribute NudgeDto nudge, Model model, UUID hostId) {
        model.addAttribute("nudge", nudge);
        nudge.setNudgedUser(userService.getUserById(hostId));
//        nudge.setNudgedUserId(hostId);
//        nudge.setSubjectName(userService.getUserById(hostId).getFirstName());
        service.saveNudge(nudge);
        return "redirect:/nudgeList";
    }

    // READ - GET

    @GetMapping("/nudgePage/{id}")
    public String nudgePage(@PathVariable long id, Model model) {
        model.addAttribute("nudge", service.getNudgeById(id));
        return "nudge-page";
    }

    @GetMapping("/nudgeList") //With filter
    public String findAllNudges(Model model) {
        model.addAttribute("nudge", service.listNudges());
        return "nudge-list";
    }

    // UPDATE - PUT
    @GetMapping("/nudgeEdit/{id}")
    public String NudgeEdit(@PathVariable long id, Model model) {
        Nudge nudge = new Nudge();
        nudge = service.getNudgeById(id);
        model.addAttribute("nudge", nudge);
        return "Nudge-edit";
    }

    @PostMapping("nudgeUpdate/{id}")
    public String updateNudge(@ModelAttribute NudgeDto nudge, @PathVariable long id) {
        service.updateNudge(nudge);
        return "redirect:/nudgePage/{id}";
    }

    // DELETE
    @GetMapping("/nudgeDelete/{id}")
    public String deleteNudge(@PathVariable long id) {
        service.deleteNudge(id);
        return "redirect:/nudge-list";
    }

}
