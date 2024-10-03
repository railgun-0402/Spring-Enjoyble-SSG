package com.example.samuraitravel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rorles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
