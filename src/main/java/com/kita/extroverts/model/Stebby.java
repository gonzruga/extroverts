package com.kita.extroverts.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="stebby")
@RequiredArgsConstructor
public class Stebby {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    private String service;
    private String serviceProvider;
    private int price;
    private String category;
    private String serviceDescription;
    private String link;

//    @ElementCollection   //This ensures that JPA handles the collection mapping appropriately.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stebby_id")
    private List<Tag> tagListStebby;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;


}
