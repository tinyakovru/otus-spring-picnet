package ru.tinyakov.picnet.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "tags")
    private Set<Pic> pics;
//    @OneToMany(mappedBy = "tag")
//    private Set<PicTag> picTags;
}
