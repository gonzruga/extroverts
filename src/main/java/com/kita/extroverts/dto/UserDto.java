package com.kita.extroverts.dto;


import com.kita.extroverts.model.Stebby;
import com.kita.extroverts.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserDto {

    private UUID id;

    private String firstName;
    private String secondName;

//    NotNull(message = "Gender is required")
//    @Size(min = 1, message = "At least one gender must be selected")
    private String gender;

    private String userEmail;
    private String mobile;
    private String telegram;

    private String profession;
    private String department;
    private String linkedIn;
    private Integer yob;

    private String bio;

    private String origin;

    private String datingObjectives;

    private String profilePicUrl;

    //Hobby Creator

    private List<Stebby> stebbyList;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

    public User dtoToUser(){
        final User user = new User();

        user.setId(id);
        user.setFirstName(firstName);
        user.setSecondName(secondName);

        if (this.gender != null && !this.gender.isEmpty()) {
            try {
                user.setGender(User.Gender.valueOf(this.gender));
            } catch (IllegalArgumentException e) {
                // Handle invalid gender value
                user.setGender(null);
            }
        }
        //        user.setGender(User.Gender.valueOf(gender));
        user.setUserEmail(userEmail);
        user.setMobile(mobile);
        user.setTelegram(telegram);
        user.setProfession(profession);
        user.setDepartment(department);
        user.setLinkedIn(linkedIn);
        user.setYob(yob);
        user.setBio(bio);

        if (this.origin != null && !this.origin.isEmpty()) {
            user.setOrigin(User.Origin.fromDisplayValue(origin));
        }
//        user.setOrigin(User.Origin.fromDisplayValue(origin));

//        user.setDatingObjectives(User.DatingObjectives.valueOf(datingObjectives));
        if (this.datingObjectives != null && !this.datingObjectives.isEmpty()) {
            try {
                user.setDatingObjectives(User.DatingObjectives.valueOf(datingObjectives));
            } catch (IllegalArgumentException e) {
                // Handle invalid datingObjectives value
                user.setDatingObjectives(null);
            }
        }


        user.setProfilePicUrl(profilePicUrl);

        user.setStebbyList(stebbyList);

        return user;

    }
    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setOrigin(user.getOrigin().getDisplayValue()); // Get display value for the origin
        return userDto;
    }

}
