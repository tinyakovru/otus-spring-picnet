package ru.tinyakov.picnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String nickname;

    private String pass;

    private String sol;

    private String avatar;

    private int rating;

    private int status;

    @OneToMany(targetEntity = Authority.class, mappedBy = "user", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Authority> authorities;

    @Column(name = "reg_date")
    private Timestamp regDate;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Pic.class, mappedBy = "userOwner")
    private Set<Pic> ownPics;

//    @JsonIgnore
//    @EqualsAndHashCode.Exclude
//    @ManyToMany(mappedBy = "usersFavorite", fetch = FetchType.LAZY)
//    private Set<Pic> favoritePics;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Favorite.class, mappedBy = "user")
    private Set<Favorite> favorites;

    public User(UserDto userDto){
        this.nickname   = userDto.getNickname();
        this.pass       = userDto.getPass();
        this.email      = userDto.getEmail();
        this.sol        = "";
    }
}
