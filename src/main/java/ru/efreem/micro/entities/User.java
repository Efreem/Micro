package ru.efreem.micro.entities;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Cacheable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "USERS")
    @JoinColumn(name = "PHONE_EMAIL")
    private Set<Phone> phoneNumbers;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROFILE_EMAIL")
    private Profile profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
