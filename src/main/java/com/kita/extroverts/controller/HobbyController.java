package com.kita.extroverts.controller;


import com.kita.extroverts.dto.HobbyDto;
import com.kita.extroverts.model.Hobby;
import com.kita.extroverts.model.Tag;
import com.kita.extroverts.repository.TagRepository;
import com.kita.extroverts.service.HobbyService;
import com.kita.extroverts.service.TagService;
import com.kita.extroverts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HobbyController {

    @Autowired
    private final HobbyService service;

    @Autowired
    private final TagService tagService;

    @Autowired
    private final TagRepository tagRepository;

    private final UserService userService;

    // CREATE - POST

    @GetMapping("/hobbyForm/{creatorId}")
    public String hobbyForm (
                             @PathVariable(name="creatorId") Long creatorId,
                             Model model){
        HobbyDto hobby = new HobbyDto();
        model.addAttribute("hobby", hobby);
        model.addAttribute("allTagsHobby", tagService.listTags());
        model.addAttribute("creatorId", creatorId);
        return "hobby-form";
    }

    @PostMapping("/hobbySubmit")
    public String hobbySubmit (@ModelAttribute HobbyDto hobby, Model model, @RequestParam("creatorId") Long creatorId) {

        model.addAttribute("hobby", hobby);

//        model.addAttribute("creatorId", creatorId); //Add to model before redirect
        Long id = creatorId; // Assign creatorId to a new parameter 'id'
        model.addAttribute("id", id); //Add to model before redirect

        hobby.setHobbyCreator(userService.getUserById(id));
        hobby.setCreatorId(id);

        List<Tag> savedTags = tagRepository.saveAll(hobby.getTagListHobby());
        hobby.setTagListHobby(savedTags);

        service.saveHobby(hobby);
//        return "redirect:/userPage/{id}";
        return "redirect:/userPage/" + id;
//        return "redirect:/hobbies";
    }

    // READ - GET

    @GetMapping("/hobbyPage/{id}")
    public String hobbyPage(@PathVariable long id, Model model) {
//        model.addAttribute("hobby", service.getHobbyById(id));  // This combines the below two steps
        Hobby hobby = service.getHobbyById(id);
        if (hobby != null) {
            model.addAttribute("hobby", hobby);
        } else {
            model.addAttribute("message", "No hobby found for this user.");
        }
        model.addAttribute("id", id);
//        model.addAttribute("hobby", hobby);
        return "hobby-page";
    }

    @GetMapping("/hobbies") //With filter
    public String findAllHobbies(Model model, @Param("keyword") String keyword) {
        log.debug("Received keyword: {}", keyword);
        model.addAttribute("hobby", service.listAll(keyword));
        model.addAttribute("keyword", keyword);
        return "hobby-list";
    }

    // UPDATE - PUT
    @GetMapping("/hobbyEdit/{id}")
    public String hobbyEdit(@PathVariable long id, Model model) {
        Hobby hobby = new Hobby();
        hobby = service.getHobbyById(id);
        model.addAttribute("hobby", hobby);
        model.addAttribute("allTagsHobby", tagService.listTags());
        return "hobby-edit";
    }

    @PostMapping("hobbyUpdate/{id}")
    public String updatehobby(@ModelAttribute HobbyDto hobby, @PathVariable long id) {
        service.updateHobby(hobby);
        return "redirect:/hobbyPage/{id}";
    }

    // DELETE
    @GetMapping("/hobbyDelete/{id}")
    public String deleteHobby(@PathVariable long id) {
        service.deleteHobby(id);
        return "redirect:/hobby-list";
    }

}
