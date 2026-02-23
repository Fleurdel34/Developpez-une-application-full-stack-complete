package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name= "subscription",
        uniqueConstraints={
                @UniqueConstraint(columnNames = {"user_id", "topic_id"})
        })
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Subscription() {
    }

    public Subscription(Date created_at) {
        this.created_at = created_at;
    }
}
