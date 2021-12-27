package com.exercise.auth.entity;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@ToString
@Entity
@Getter
@Setter
@Table(name="AUTH_USER")
public class User {

    @Id
    private long seq;
    @JoinColumn(nullable = false)
    private String id;
    @Column(nullable = false)
    private String pwd;
    @JoinColumn
    private String nickname;
    @Column(nullable = false)
    private String address;
    @Column
    private String intro;
    @Column
    private String name;
    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column
    private Date regDate;
    @Column(nullable = false)
    private String birthDate;

    public User() {}


}
