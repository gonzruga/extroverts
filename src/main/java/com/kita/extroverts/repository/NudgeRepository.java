package com.kita.extroverts.repository;

import com.kita.extroverts.model.Nudge;
import com.kita.extroverts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NudgeRepository extends JpaRepository<Nudge, Long> {

    List<Nudge> findAllByNudgedUserId(Long id);

//    List<Nudge> findAllByNudgedUser(User user);

}
