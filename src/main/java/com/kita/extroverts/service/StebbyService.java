package com.kita.extroverts.service;

import com.kita.extroverts.dto.StebbyDto;
import com.kita.extroverts.model.Stebby;
import com.kita.extroverts.repository.StebbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StebbyService {

    @Autowired
    private final StebbyRepository repository;

    // CREATE - POST

    public Stebby saveStebby(StebbyDto Stebby) { return repository.save(Stebby.dtoToStebby()); }

    // READ - GET
    public Stebby getStebbyById(Long id) { return repository.findById(id).orElse(null); }

    public List<Stebby> listAll(String keyword) {
        if (keyword != null){
            return repository.findAll(keyword);
        }
        return repository.findAll();
    }

    public List<Stebby> listStebbies() { return repository.findAll(); }

    // DELETE

    public String deleteStebby(long id){
        repository.deleteById(id);
        return "Removed Stebby ID: " + id;
    }

    // UPDATE - PUT
    public Stebby updateStebby(StebbyDto Stebby){
        Stebby existingStebby = repository.findById(Stebby.getId()).orElse(null);

        existingStebby.setService(Stebby.getService());
        existingStebby.setServiceProvider(Stebby.getServiceProvider());
        existingStebby.setPrice(Stebby.getPrice());
        existingStebby.setCategory(Stebby.getCategory());
        existingStebby.setServiceDescription(Stebby.getServiceDescription());
        existingStebby.setLink(Stebby.getLink());

        existingStebby.setTagListStebby(Stebby.getTagListStebby());

        existingStebby.setUpdatedAt(new Date());

        return repository.save(existingStebby);

    }

}
