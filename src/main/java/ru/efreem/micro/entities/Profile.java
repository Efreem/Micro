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

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
