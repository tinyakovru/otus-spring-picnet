package ru.tinyakov.picnet.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Pic {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String descr;

    //url large picture
    @Column(name = "url_l")
    private String urlL;

    //url small picture
    @Column(name = "url_s")
    private String urlS;

    //url very small picture
    @Column(name = "url_xs")
    private String urlXS;

    private int rating;
    private int status;

    @Column(name = "load_date")
    private Timestamp loadDate;

    @Column(name = "moder_date")
    private Timestamp moderDate;

    //    @Column(name = "user_id")
//    private long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userOwner;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = "favorite",
            joinColumns = @JoinColumn(name = "pic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> usersFavorite;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, mappedBy = "pic")
    Set<Comment> comments;

    @ManyToMany
    @JoinTable(name = "pic_tag",
            joinColumns = @JoinColumn(name = "pic_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
}
