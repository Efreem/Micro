package ru.efreem.micro.model;

import javax.persistence.*;

@Entity
@Table(name = "PHONES")
public class Phone extends BaseEntity {
    @Column(name = "VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_EMAIL", referencedColumnName = "EMAIL")
    private User user;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
