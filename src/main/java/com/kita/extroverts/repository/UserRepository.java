package com.kita.extroverts.repository;

import com.kita.extroverts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

@Query("SELECT u FROM User u left join u.stebbyList s WHERE u.firstName LIKE %?1%" +
        "OR u.secondName LIKE %?1%" +
        "OR u.bio LIKE %?1%" +
        "OR s.service LIKE %?1%" +
        "OR s.serviceDescription LIKE %?1%"
        )

    public List<User> findAll(String keyword);

}
