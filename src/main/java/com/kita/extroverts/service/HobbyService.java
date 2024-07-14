package com.kita.extroverts.service;

import com.kita.extroverts.dto.HobbyDto;
import com.kita.extroverts.model.Hobby;
import com.kita.extroverts.repository.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyService {

    @Autowired
    private final HobbyRepository repository;

    // CREATE - POST

    public Hobby saveHobby(HobbyDto Hobby) { return repository.save(Hobby.dtoToHobby()); }

    // READ - GET
    public Hobby getHobbyById(Long id) { return repository.findById(id).orElse(null); }

    public List<Hobby> listAll(String keyword) {
        if (keyword != null){
            return repository.findAll(keyword);
        }
        return repository.findAll();
    }

    public List<Hobby> listHobbies() { return repository.findAll(); }

//    public Hobby findHobbyByCreatorId(Long creatorId) {
//        return repository.findByHobbyCreatorId(creatorId);
//    }

    // DELETE

    public String deleteHobby(long id){
        repository.deleteById(id);
        return "Removed Hobby ID: " + id;
    }

    // UPDATE - PUT
    public Hobby updateHobby(HobbyDto Hobby){
        Hobby existingHobby = repository.findById(Hobby.getId()).orElse(null);

        existingHobby.setTitle(Hobby.getTitle());
        existingHobby.setServiceProvider(Hobby.getServiceProvider());
        existingHobby.setPrice(Hobby.getPrice());
        existingHobby.setHobbyDescription(Hobby.getHobbyDescription());
        existingHobby.setLink(Hobby.getLink());

//        existingHobby.setCreatorId(Hobby.getCreatorId());  //TODO: Revise
        existingHobby.setHobbyCreator(Hobby.getHobbyCreator());

        existingHobby.setTagListHobby(Hobby.getTagListHobby());

        existingHobby.setUpdatedAt(new Date());

        return repository.save(existingHobby);

    }



}
