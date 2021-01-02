package ru.efreem.micro.model;

import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PROFILES")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AGE")
    private Byte age;
    @Column(name = "CASH")
    private BigDecimal cash;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_EMAIL", referencedColumnName = "EMAIL")
    private User user;

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public synchronized BigDecimal getCash() {
        return cash;
    }

    public synchronized void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equalsById(Profile profile) {
        return this.id.equals(profile.getId());
    }

    /*
    Данный алгоритм хэширования заключается в том, что идентифиакатор и возраст переводятся в строку,
    после чего из их объединения вычисляется хэш. Данный алгоритм является предварительным. В будущем
    планируется его замена на более эффективный
     */
    @Override
    public int hashCode() {
        String idStringValue = id.toString();
        String ageStringValue = age.toString();
        String concat = idStringValue + ageStringValue;

        return concat.hashCode();
    }
}
