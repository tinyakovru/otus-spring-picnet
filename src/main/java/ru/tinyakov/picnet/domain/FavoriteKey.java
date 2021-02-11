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
public class FavoriteKey implements Serializable {
    @Column(name = "user_id")
    private long userId;

    @Column(name = "pic_id")
    private long picId;
}
