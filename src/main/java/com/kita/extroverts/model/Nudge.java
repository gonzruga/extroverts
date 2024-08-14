package com.kita.extroverts.model;

import jakarta.persistence.*;  //Entity, GeneratedValue, id,Table, Column
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="nudge")
@RequiredArgsConstructor
public class Nudge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    private int yourProfileId;
    private String contacts;
    private String comment;
    private String subjectId;
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "nudged_user")
    private User nudgedUser;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

}
