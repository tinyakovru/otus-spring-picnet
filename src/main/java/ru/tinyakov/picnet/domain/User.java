package ru.tinyakov.picnet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.tinyakov.picnet.domain.dto.UserDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Data
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private long id;

    @ToString.Include
    private String email;

    @ToString.Include
    private String nickname;

    private String pass;

    private String sol;

    private String avatar;

    @ToString.Include
    private int rating;

    private int status;

    @OneToMany(targetEntity = Authority.class, mappedBy = "user", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Authority> authorities;

    @Column(name = "reg_date")
    private Timestamp regDate;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Pic.class, mappedBy = "userOwner")
    private Set<Pic> ownPics;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Comment.class, mappedBy = "user")
    private List<Comment> comments;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Favorite.class, mappedBy = "user")
    private Set<Favorite> favorites;

    public User(UserDto userDto) {
        this.nickname = userDto.getNickname();
        this.pass = userDto.getPass();
        this.email = userDto.getEmail();
        this.sol = "";
    }
}
