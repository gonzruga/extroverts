package com.kita.extroverts.service;


import com.kita.extroverts.dto.NudgeDto;
import com.kita.extroverts.model.Nudge;
import com.kita.extroverts.repository.NudgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NudgeService {

    private final NudgeRepository repository;

    private final UserService userService; // To connect Nudge to a User.

    // CREATE - POST
    public Nudge saveNudge(NudgeDto Nudge) {

        return repository.save(Nudge.dtoToNudge());}

    // READ - GET
    public Nudge getNudgeById(Long id) { return repository.findById(id).orElse(null); }

    public List<Nudge> listNudges() { return repository.findAll(); }

    public List<Nudge> getUserNudges(Long NudgedUserId) {
        return repository.findAllByNudgedUserId(NudgedUserId);

    }

    // DELETE
    public String deleteNudge(long id){
        repository.deleteById(id);
        return "Removed Nudge ID: " + id;
    }

    // UPDATE - PUT
    public Nudge updateNudge(NudgeDto Nudge) {
        Nudge existingNudge = repository.findById(Nudge.getId()).orElse(null);

        existingNudge.setYourProfileId(Nudge.getYourProfileId());
        existingNudge.setContacts(Nudge.getContacts());
        existingNudge.setComment(Nudge.getComment());
        existingNudge.setSubjectId(Nudge.getSubjectId());
        existingNudge.setSubjectName(Nudge.getSubjectName());

        existingNudge.setUpdatedAt(new Date());

        return repository.save(existingNudge);
    }
}
