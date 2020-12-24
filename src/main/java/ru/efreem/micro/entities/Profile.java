package ru.efreem.micro.entities;

import javax.persistence.*;

@Entity
@Table(name = "PROFILES")
@Cacheable
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "AGE")
    private byte age;
    @Column(name = "CASH")
    private double cash;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_EMAIL")
    private User user;
}
