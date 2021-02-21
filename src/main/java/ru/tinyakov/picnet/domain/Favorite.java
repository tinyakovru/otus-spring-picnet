package ru.tinyakov.picnet.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "favorite")
@NoArgsConstructor
//@RequiredArgsConstructor
@EqualsAndHashCode
public class Favorite {
    @EmbeddedId
    private FavoriteKey favoriteKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pic_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private Pic pic;

    public Favorite(FavoriteKey favoriteKey) {
        this.favoriteKey = favoriteKey;
    }
}
