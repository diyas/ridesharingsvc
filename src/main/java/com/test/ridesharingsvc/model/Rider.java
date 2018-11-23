package com.test.ridesharingsvc.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "riders")
@Data
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "rider_id")
    private String riderId;
    @Column(name = "rider_name")
    private String riderName;
    private String email;
    private String password;
    @Column(name = "sts_rider")
    private String stsRider;
}
