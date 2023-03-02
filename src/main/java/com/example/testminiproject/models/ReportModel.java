package com.example.testminiproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name",nullable = false)
    private String name;

    @Column(name = "DormName",nullable = false)
    private String dormName;

    @Column(name = "Room",nullable = false)
    private int room;

    @Column(name = "Details",nullable = false)
    private String details;

    @Column(name = "Image",nullable = false)
    private String image;
}
