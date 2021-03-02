package ru.tinyakov.picnet.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteKey implements Serializable {
    @Column(name = "user_id")
    @NotNull
    private long userId;

    @Column(name = "pic_id")
    @NotNull
    private long picId;

}
