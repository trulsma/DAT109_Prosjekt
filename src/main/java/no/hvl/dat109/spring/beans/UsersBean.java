package no.hvl.dat109.spring.beans;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "prosjekt1")
public class UsersBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    private String username, passord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usergroup")
    private UserGroupBean userGroup;
}
