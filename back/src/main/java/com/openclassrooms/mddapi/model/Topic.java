package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name= "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String title;

    @Column (nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "topic")
    private List<Subscription> subscriptions;

    public Topic() {
    }

    public Topic(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
