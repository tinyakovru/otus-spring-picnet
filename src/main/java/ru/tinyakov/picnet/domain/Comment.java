package ru.tinyakov.picnet.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private Timestamp date = new Timestamp(System.currentTimeMillis());


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pic.class)
    @JoinColumn(name = "pic_id")
    private Pic pic;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    private int status = 1;
}
