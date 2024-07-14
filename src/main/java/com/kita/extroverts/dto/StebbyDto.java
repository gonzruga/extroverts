package com.kita.extroverts.dto;

import com.kita.extroverts.model.Stebby;
import com.kita.extroverts.model.Tag;
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
public class StebbyDto {

    private Long id;

    private String service;
    private String serviceProvider;
    private int price;
    private String category;
    private String serviceDescription;
    private String link;

    private List<Tag> tagListStebby;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt = null;

    public Stebby dtoToStebby (){
        final Stebby stebby = new Stebby();

        stebby.setId(id);
        stebby.setService(service);
        stebby.setServiceProvider(serviceProvider);
        stebby.setPrice(price);
        stebby.setCategory(category);
        stebby.setServiceDescription(serviceDescription);
        stebby.setLink(link);

        stebby.setTagListStebby(tagListStebby);

        return stebby;

    }

}
