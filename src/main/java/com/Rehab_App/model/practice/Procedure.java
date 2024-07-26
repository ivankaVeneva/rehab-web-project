package com.Rehab_App.model.practice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "coordinates", columnDefinition = "LONGTEXT")
    private String Coordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne(optional = false)
    private Participant author;

    @OneToMany(targetEntity = Comment.class, mappedBy = "procedure")
    private Set<Comment> comments;

    @OneToMany(targetEntity = Picture.class, mappedBy = "procedure")
    private Set<Picture> pictures;

    @ManyToMany
    private Set<Category> categories;

    public Procedure() {
        this.comments = new HashSet<>();
        this.pictures = new HashSet<>();
        this.categories = new HashSet<>();
    }
}