package com.kita.extroverts.service;

import com.kita.extroverts.dto.HostRequestDto;
import com.kita.extroverts.model.HostRequest;
import com.kita.extroverts.repository.HostRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HostRequestService {

    @Autowired
    private final HostRequestRepository repository;

    @Autowired
    private final HobbyService hobbyService;

    // CREATE
    public HostRequest saveHostRequest(HostRequestDto HostRequest) { return repository.save(HostRequest.dtoToHostRequest());
    }
    // READ
    public HostRequest getHostRequestById(UUID id) { return repository.findById(id).orElse(null); }

    public List<HostRequest> listAll() { return repository.findAll(); }
    // DELETE
    public String deleteHostRequest(UUID id) {repository.deleteById(id); return "HostRequest removed ID: " + id; }

    //UPDATE
    public HostRequest updateHostRequest(HostRequestDto messsage) {
        HostRequest existingHostRequest = repository.findById(messsage.getId()).orElse(null);
        existingHostRequest.setComment(messsage.getComment());
        existingHostRequest.setUpdatedAt(new Date());
        return repository.save(existingHostRequest);
    }
}
