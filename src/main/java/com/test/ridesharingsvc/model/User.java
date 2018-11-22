package com.test.ridesharingsvc.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
//@EntityListeners(AuditingEntityListener.class)
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private String userId;
    private String name;
    private String email;
    private String password;
    @Column(name = "sts_usr")
    private String stsUsr;

}
