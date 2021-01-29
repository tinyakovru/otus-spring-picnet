package ru.tinyakov.picnet.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String email;

    private String nickname;

    private String pass;

    private String sol;

    private String avatar;

    private int rating;

    private int status;

    @Column(name = "reg_date")
    private Timestamp regDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Pic.class, mappedBy = "userOwner")
    private Set<Pic> ownPics;

    @ManyToMany(mappedBy = "usersFavorite")
    private Set<Pic> favoritePics;
}
