package com.Rehab_App.model.practice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private Instant created;

    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private Participant author;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Procedure procedure;
}
