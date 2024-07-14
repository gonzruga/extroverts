package com.kita.extroverts.repository;

import com.kita.extroverts.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    @Query("SELECT h FROM Hobby h left join h.tagListHobby t WHERE h.title LIKE %?1%" +
            "OR h.serviceProvider LIKE %?1%" +
            "OR h.hobbyDescription LIKE %?1%" +
            "OR h.link LIKE %?1%" +

            "OR t.tagTitle LIKE %?1%" +
            "OR t.tagDescription LIKE %?1%"
    )

    public List<Hobby> findAll(String keyword);

}
