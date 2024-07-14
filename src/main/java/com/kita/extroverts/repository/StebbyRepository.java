package com.kita.extroverts.repository;

import com.kita.extroverts.model.Stebby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StebbyRepository extends JpaRepository<Stebby, Long> {

    @Query("SELECT s FROM Stebby s left join s.tagListStebby t WHERE s.service LIKE %?1%" +
            "OR s.serviceProvider LIKE %?1%" +
            "OR s.category LIKE %?1%" +
            "OR s.serviceDescription LIKE %?1%" +
            "OR s.link LIKE %?1%" +

            "OR t.tagTitle LIKE %?1%" +
            "OR t.tagDescription LIKE %?1%"
    )
    public List<Stebby> findAll(String keyword);

}
