package com.kita.extroverts.controller;


import com.kita.extroverts.dto.UserDto;
import com.kita.extroverts.model.Stebby;
import com.kita.extroverts.model.User;
import com.kita.extroverts.repository.StebbyRepository;
import com.kita.extroverts.service.StebbyService;
import com.kita.extroverts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@RestController  // Use with Postman. For returning data, not template
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private StebbyService stebbyService;

    @Autowired
    private StebbyRepository stebbyRepository;

    // CREATE - POST
    @GetMapping("/userForm")
    public String userForm(Model model) {
//        model.addAttribute("userDto", new UserDto());
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("allStebbies", stebbyService.listStebbies());
        return "user-form";
    }

    @PostMapping("/userCreate")
    public String saveUser(@ModelAttribute UserDto user, Model model){
        model.addAttribute("user", user);  // Saving user object to the db
        //Before calling saveAll(), check if stebbyList is neither null nor contains null elements.
        List<Stebby> stebbyList = user.getStebbyList();
        if (stebbyList != null && !stebbyList.contains(null)) {
            List<Stebby> savedStebbies = stebbyRepository.saveAll(stebbyList);
            user.setStebbyList(savedStebbies);
        }




//        List<Stebby> savedStebbies = stebbyRepository.saveAll(user.getStebbyList());
//        user.setStebbyList(savedStebbies);

        service.saveUser(user);
        return "redirect:/about";
    }

// READ - GET
    @GetMapping("/users")
    public String findAllUsers(Model model, @Param("keyword") String keyword) {
        model.addAttribute("user", service.filterList(keyword));
        model.addAttribute("keyword", keyword);
        return "user-list";
    }

    @GetMapping("/userPage/{id}")
    public String userPage(@PathVariable UUID id, Model model) {
        User userById = service.getUserById(id);
        model.addAttribute("user", userById);
        return "user-page";
    }

    @GetMapping("/userSearch")
    public String userSearch(Model model){
        model.addAttribute("user", new User());
        return "user-page-search";
    }

    @PostMapping("/userSearched")
    public String searchUser(@RequestParam UUID id) {
        return "redirect:/userPage/" + id;
    }

    // UPDATE - PUT

    @GetMapping("/userEdit/{id}")
    public String editUser(@PathVariable UUID id, Model model) {
        User user = new User();
        user = service.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allStebbies", stebbyService.listStebbies());

        return "user-edit";
    }

    @PostMapping("/userUpdate/{id}")
    public String updateUser(@ModelAttribute UserDto user, @PathVariable UUID id) {
        if (user.getStebbyList() != null) {
            for (Stebby stebby : user.getStebbyList()){
                System.out.println("Stebby: " + stebby);
            }
            List<Stebby> savedStebbies = stebbyRepository.saveAll(user.getStebbyList());
            user.setStebbyList(savedStebbies);
        }

//        List<Stebby> savedStebbies = stebbyRepository.saveAll(user.getStebbyList());
//        user.setStebbyList(savedStebbies);

        service.updateUser(user);
//        return "redirect:/userPage/" + id;
        return "redirect:/userPage/{id}";
    }

//    Test with Postman
    @PostMapping("/userUpdate2/{id}")
    public User updateUser2(@RequestBody UserDto user, @PathVariable long id) {
        return service.updateUser(user);
    }

// DELETE

    @GetMapping("/userDelete/{id}")
    public String deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

}