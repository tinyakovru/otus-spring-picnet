package ru.tinyakov.picnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@EqualsAndHashCode
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private long id;

    @ToString.Include
    private String title;
    private String descr;

    //url large picture
    @Column(name = "url_l")
    @ToString.Include
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User userOwner;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Favorite.class, mappedBy = "pic")
    private Set<Favorite> favorites;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, mappedBy = "pic")
    private Set<Comment> comments;

    @ManyToMany
    @Fetch(FetchMode.SUBSELECT)
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "pic_tag",
            joinColumns = @JoinColumn(name = "pic_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
}
