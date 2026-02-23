package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name= "comment",
        uniqueConstraints={
        @UniqueConstraint(columnNames = {"user_id", "article_id"})
})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String content;

    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    public Comment() {
    }

    public Comment(String content, Date created_at) {
        this.content = content;
        this.created_at = created_at;
    }
}
