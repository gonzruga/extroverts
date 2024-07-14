package com.kita.extroverts.dto;


import com.kita.extroverts.model.Nudge;
import com.kita.extroverts.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Table(name="nudge")
@RequiredArgsConstructor
public class NudgeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    private int yourProfileId;
    private String contacts;
    private String comment;
    private String subjectId;
    private String subjectName;

    private Long nudgedUserId;
    private User nudgedUser;   //Used in NudgeController: 'nudgeSubmit'

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

    public Nudge dtoToNudge () {
        final Nudge nudge = new Nudge();

        nudge.setId(id);
        nudge.setContacts(contacts);
        nudge.setComment(comment);
        nudge.setSubjectId(subjectId);
        nudge.setSubjectName(subjectName);

        nudge.setNudgedUser(nudgedUser);

        return nudge;
    }


}
