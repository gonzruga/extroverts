package com.kita.extroverts.dto;

import com.kita.extroverts.model.Hobby;
import com.kita.extroverts.model.Stebby;
import com.kita.extroverts.model.Tag;
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
public class HobbyDto {

    private Long id;

    private String title;
    private String serviceProvider;
    private int price;
    private String hobbyDescription;
    private String link;

    private Long creatorId;
    private User hobbyCreator;

    private List<Tag> tagListHobby;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

    public Hobby dtoToHobby (){
        final Hobby hobby = new Hobby();

        hobby.setId(id);
        hobby.setTitle(title);
        hobby.setServiceProvider(serviceProvider);
        hobby.setPrice(price);
        hobby.setHobbyDescription(hobbyDescription);
        hobby.setLink(link);

        hobby.setHobbyCreator(hobbyCreator);

        hobby.setTagListHobby(tagListHobby);

        return hobby;

    }

}
