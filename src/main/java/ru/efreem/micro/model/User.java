package ru.efreem.micro.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Cacheable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Id
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL")
    private Set<Phone> phoneNumbers;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL", insertable = false, updatable = false)
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email1;
    }

    public void setEmail(String email) {
        this.email = email
    }

    public Set<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
