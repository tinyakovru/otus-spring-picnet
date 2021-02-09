package ru.tinyakov.picnet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class AuthorityKey implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "role")
    private String role;
}
