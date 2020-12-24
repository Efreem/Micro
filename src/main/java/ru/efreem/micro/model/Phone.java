package ru.efreem.micro.model;

import javax.persistence.*;

@Entity
@Table(name = "PHONES")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_EMAIL")
    private User user;
}
