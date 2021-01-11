package ru.efreem.micro.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Cacheable
public class User extends BaseEntity {
    @Column(name = "NAME")
    private String name;
    @NaturalId
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PHONE_EMAIL")
    private Set<Phone> phones;

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

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean equalsByNameAndId(User user) {
        return this.id.equals(user.getId()) && this.name.equals(user.getName());
    }
}
