package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Implements entity
 * implements model article
 */

@Getter
@Setter
@Entity
@Table(name= "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String topic;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    public Article() {
    }

    public Article(String topic, String title, String content, Date created_at) {
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
    }
}
