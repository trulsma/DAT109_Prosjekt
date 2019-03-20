package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usergroup", schema = "prosjekt1")
public class UserGroupBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupid;

    private String groupname;

    private int grouplevel;

    @OneToMany(mappedBy = "userGroup")
    private List<UsersBean> users;

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getGrouplevel() {
        return grouplevel;
    }

    public void setGrouplevel(int grouplevel) {
        this.grouplevel = grouplevel;
    }

    @Override
    public String toString() {
        return "UserGroupBean{" +
                "groupid=" + groupid +
                ", groupname='" + groupname + '\'' +
                ", grouplevel=" + grouplevel +
                '}';
    }
}
