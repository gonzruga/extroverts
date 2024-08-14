package com.kita.extroverts.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="host_request")
@RequiredArgsConstructor
public class HostRequest {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    private UUID yourProfileId;
    private String contacts;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

}
