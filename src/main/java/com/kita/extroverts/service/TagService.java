package com.kita.extroverts.service;


import com.kita.extroverts.dto.TagDto;
import com.kita.extroverts.model.Tag;
import com.kita.extroverts.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    @Autowired
    private final TagRepository repository;

    // CREATE - POST

    public Tag saveTag(TagDto tag) { return repository.save(tag.dtoToTag()); }

    // READ - GET
    public Tag getTagById(Long id) { return repository.findById(id).orElse(null); }

    public List<Tag> listAll(String keyword) {
        if (keyword != null){
            return repository.findAll(keyword);
        }
        return repository.findAll();
    }

    public List<Tag> listTags() {
        return repository.findAll();
    }

    // DELETE - 

    public String deleteTag(long id){
        repository.deleteById(id);
        return "Removed Tag ID: " + id;
    }

    // UPDATE - PUT
    public Tag updateTag(TagDto tag){
        Tag existingTag = repository.findById(tag.getId()).orElse(null);

        existingTag.setTagTitle(tag.getTagTitle());
        existingTag.setTagDescription(tag.getTagDescription());

        existingTag.setUpdatedAt(new Date());

        return repository.save(existingTag);

    }
    
}
