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
@Table(name="user")
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false)
    private Long id;

    private String firstName;
    private String secondName;

    @Enumerated(EnumType.STRING)
    private Gender gender;  // Gender enum defines the possible values for the gender field, ensuring that only 'male' or 'female' can be assigned.

    @Column(unique = true)
    private String userEmail;
    private String mobile;
    private String telegram;

    private String profession;
    private String department;
    private String linkedIn;
    private Integer yob;

    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(name = "origin", nullable = false, length = 20) // Adjust length as needed
    private Origin origin;

    @Enumerated(EnumType.STRING)
    @Column(name = "dating_objectives", nullable = false, length = 20) // Adjust length as needed
    private DatingObjectives datingObjectives;

    // This variable is used when storing images to AWS and Fasthub CDN.Utils
    private String profilePicUrl;

    @OneToMany(mappedBy = "hobbyCreator", fetch = FetchType.EAGER)
    private Set<Hobby> hobbies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") //Todo: revise
    private List<Stebby> stebbyList;

    @OneToMany(mappedBy = "nudgedUser", fetch = FetchType.EAGER)
    private Set<Nudge> nudges;
    // Array is not good because fetching them can cause errors. Instead I use Set

    @OneToMany(mappedBy = "reviewee", fetch = FetchType.EAGER)
    private Set<Review> reviews;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

//    ENUMS CODE
    public enum Gender { Male, Female }

    public enum DatingObjectives { Looking, Whatever, No }

    public enum Origin {
        ESTONIAN("Estonian"),
        EUROPEAN("European"),
        ASIAN("Asian"),
        NORTH_AMERICAN("North American"),
        SOUTH_AMERICAN("South American"),
        RUSSIAN("Russian"),
        AFRICAN("African"),
        OTHERS("Others");

        private final String displayValue;

        Origin(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }

        public static Origin fromDisplayValue(String displayValue) {
            for (Origin origin : Origin.values()) {
                if (origin.getDisplayValue().equalsIgnoreCase(displayValue)) {
                    return origin;
                }
            }
            throw new IllegalArgumentException("No constant with display value: " + displayValue);
        }

    }
}

