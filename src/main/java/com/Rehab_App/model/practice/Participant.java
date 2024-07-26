package com.Rehab_App.model.practice;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private Integer age;

    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Level level;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<ParticipantRole> participantRoles;
//
//    public Participant() {
//        this.participantRoles = new HashSet<>();
//    }
}
