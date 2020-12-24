package ru.efreem.micro.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PROFILES")
@Cacheable
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AGE")
    private byte age;
    @Column(name = "CASH")
    private BigDecimal cash;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_EMAIL")
    private User user;

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
