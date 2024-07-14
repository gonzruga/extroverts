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

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;

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
        user.setGender(User.Gender.valueOf(gender));
        user.setUserEmail(userEmail);
        user.setMobile(mobile);
        user.setTelegram(telegram);
        user.setProfession(profession);
        user.setDepartment(department);
        user.setLinkedIn(linkedIn);
        user.setYob(yob);
        user.setBio(bio);

        user.setOrigin(User.Origin.fromDisplayValue(origin));
        user.setDatingObjectives(User.DatingObjectives.valueOf(datingObjectives));

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
