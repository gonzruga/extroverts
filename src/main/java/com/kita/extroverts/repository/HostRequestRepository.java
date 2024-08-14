package com.kita.extroverts.repository;

import com.kita.extroverts.model.HostRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HostRequestRepository extends JpaRepository<HostRequest, UUID> {

}
