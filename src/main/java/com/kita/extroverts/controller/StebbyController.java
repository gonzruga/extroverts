package com.kita.extroverts.controller;


import com.kita.extroverts.dto.StebbyDto;
import com.kita.extroverts.model.Stebby;
import com.kita.extroverts.model.Tag;
import com.kita.extroverts.repository.TagRepository;
import com.kita.extroverts.service.StebbyService;
import com.kita.extroverts.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StebbyController {

    @Autowired
    private final StebbyService service;

    @Autowired
    private final TagService tagService;

    @Autowired
    private final TagRepository tagRepository;

    // CREATE - POST

    @GetMapping("/stebbyForm")
    public String stebbyForm (Model model){
        StebbyDto stebby = new StebbyDto();
        model.addAttribute("stebby", stebby);
        model.addAttribute("allTagsStebby", tagService.listTags());
        return "stebby-form";
    }

    @PostMapping("/stebbySubmit")
    public String saveStebby (@ModelAttribute StebbyDto stebby, Model model) {
        model.addAttribute("stebby", stebby);
        List<Tag> savedTags = tagRepository.saveAll(stebby.getTagListStebby());
        stebby.setTagListStebby(savedTags);

        service.saveStebby(stebby);
        return "redirect:/stebbies";
    }

    // READ - GET

    @GetMapping("/stebbyPage/{id}")
    public String stebbyPage(@PathVariable long id, Model model) {
        model.addAttribute("stebby", service.getStebbyById(id));
        return "stebby-page";
    }

    @GetMapping("/stebbies") //With filter
    public String findAllStebbies(Model model, @Param("keyword") String keyword) {
//        log.debug("Received keyword: {}", keyword);
        model.addAttribute("stebby", service.listAll(keyword));
        model.addAttribute("keyword", keyword);
        return "stebby-list";
    }

    // UPDATE - PUT
    @GetMapping("/stebbyEdit/{id}")
    public String stebbyEdit(@PathVariable long id, Model model) {
        Stebby stebby = new Stebby();
        stebby = service.getStebbyById(id);
        model.addAttribute("stebby", stebby);
        model.addAttribute("allTagsStebby", tagService.listTags());
        return "stebby-edit";
    }

    @PostMapping("stebbyUpdate/{id}")
    public String updateStebby(@ModelAttribute StebbyDto stebby, @PathVariable long id) {
        service.updateStebby(stebby);
        return "redirect:/stebbyPage/{id}";
    }

    // DELETE
    @GetMapping("/stebbyDelete/{id}")
    public String deleteStebby(@PathVariable long id) {
        service.deleteStebby(id);
        return "redirect:/stebby-list";
    }

}
