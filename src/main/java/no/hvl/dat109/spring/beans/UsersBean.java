package no.hvl.dat109.spring.beans;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "prosjekt1")
public class UsersBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    private String username, password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usergroup")
    private UserGroupBean userGroup;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroupBean getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupBean userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public String toString() {
        return "UsersBean{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userGroup=" + userGroup +
                '}';
    }
}
