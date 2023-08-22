package com.IlyaJukov.SocialMedia.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor @Getter @Setter
@Table(name = "post", uniqueConstraints = { @UniqueConstraint(columnNames = "title") })
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;
    private String description;
    private String text;
    private String imgURL;
    private Date dateCreated;

    public Post(Long userId, String title, String description, String text, String imgURL) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.text = text;
        this.imgURL = imgURL;
        this.dateCreated = new Date();
    }
}
