package ru.tinyakov.picnet.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Authority implements GrantedAuthority {
    @EmbeddedId
    private AuthorityKey authorityKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role", insertable = false, updatable = false, nullable = false)
    private String role;

    @Override
    public String getAuthority() {
        System.out.println("get auth="+role);
        return role;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityKey=" + authorityKey +
                ", user name=" + user.getNickname() +
                ", role='" + role + '\'' +
                '}';
    }
}
