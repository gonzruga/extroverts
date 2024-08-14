package com.kita.extroverts.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="hobby")
@RequiredArgsConstructor
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    private String title;
    private String serviceProvider;
    private int price;
    private String hobbyDescription;
    private String link;

//    private Long creatorId;

    @ManyToOne
    @JoinColumn(name = "creator_id", columnDefinition = "BINARY(16)")
    private User hobbyCreator;

//    @ElementCollection   //This ensures that JPA handles the collection mapping appropriately.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hobby_id")
    private List<Tag> tagListHobby;

    @OneToMany(mappedBy = "hobby", fetch = FetchType.EAGER)
    private Set<HostRequest> hostRequests;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;


}
