package com.kita.extroverts.dto;


import com.kita.extroverts.model.Hobby;
import com.kita.extroverts.model.HostRequest;
import com.kita.extroverts.model.Nudge;
import com.kita.extroverts.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class HostRequestDto {

    private UUID id;

    private UUID yourProfileId;
    private String contacts;
    private String comment;

    private Long hobbyId;
    private Hobby hobby;   //Used in HRController: 'nudgeSubmit'

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

    public HostRequest dtoToHostRequest () {
        final HostRequest hostRequest = new HostRequest();

        hostRequest.setId(id);
        hostRequest.setYourProfileId(yourProfileId);
        hostRequest.setContacts(contacts);
        hostRequest.setComment(comment);

        hostRequest.setHobby(hobby);

        return hostRequest;
    }


}
