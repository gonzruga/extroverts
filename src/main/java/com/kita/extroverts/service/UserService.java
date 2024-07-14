package com.kita.extroverts.service;

import com.kita.extroverts.dto.UserDto;
import com.kita.extroverts.model.User;
import com.kita.extroverts.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;


    // CREATE - POST
    public User saveUser(UserDto user) {return repository.save(user.dtoToUser());}

    // READ - GET
    public User getUserById(long id) {return repository.findById(id).orElse(null);}

    public List<User> getUsers() {return repository.findAll();}

    public List<User> filterList(String keyword) {
        if (keyword != null) {
            return repository.findAll(keyword);
        }
        return repository.findAll();}

    // UPDATE - PUT

    public User updateUser(UserDto user) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setSecondName(user.getSecondName());
        existingUser.setGender(User.Gender.valueOf(user.getGender()));
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setMobile(user.getMobile());
        existingUser.setTelegram(user.getTelegram());
        existingUser.setProfession(user.getProfession());
        existingUser.setDepartment(user.getDepartment());
        existingUser.setLinkedIn(user.getLinkedIn());
        existingUser.setYob(user.getYob());
        existingUser.setBio(user.getBio());

        existingUser.setProfilePicUrl(user.getProfilePicUrl());

        existingUser.setOrigin(User.Origin.fromDisplayValue(user.getOrigin()));

        existingUser.setDatingObjectives(User.DatingObjectives.valueOf(user.getDatingObjectives()));

        existingUser.setStebbyList(user.getStebbyList());

        existingUser.setUpdatedAt(new Date());

        repository.save(existingUser);
        return existingUser;
    }
    // DELETE

    public String deleteUser(long id){
//        repository.deleteAllById(Collections.singleton(id));
//        TODO: Revise/review logs
        log.info("Removing user with ID number: " + id);
        repository.deleteById(id);
        log.info("User removed with ID number: " + id);
        return "User removed with ID number: " + id;
    }

}
