package com.kita.extroverts.repository;

import com.kita.extroverts.model.Nudge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NudgeRepository extends JpaRepository<Nudge, Long> {

    List<Nudge> findAllByNudgedUserId(UUID id);

//    List<Nudge> findAllByNudgedUser(User user);

}
