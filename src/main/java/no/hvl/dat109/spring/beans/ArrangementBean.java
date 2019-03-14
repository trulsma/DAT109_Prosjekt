package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "arrangement", schema = "prosjekt1")
public class ArrangementBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int arrangementid;
    private String arrangementnavn;
    private String arrangementbeskrivelse;
    private Timestamp arragementetutgaar;

    @Column(name = "arrangementid")
    public int getArrangementid() {
        return arrangementid;
    }

    public void setArrangementid(int arrangementid) {
        this.arrangementid = arrangementid;
    }

    @Basic
    @Column(name = "arrangementnavn")
    public String getArrangementnavn() {
        return arrangementnavn;
    }

    public void setArrangementnavn(String arrangementnavn) {
        this.arrangementnavn = arrangementnavn;
    }

    @Basic
    @Column(name = "arrangementbeskrivelse")
    public String getArrangementbeskrivelse() {
        return arrangementbeskrivelse;
    }

    public void setArrangementbeskrivelse(String arrangementbeskrivelse) {
        this.arrangementbeskrivelse = arrangementbeskrivelse;
    }

    @Basic
    @Column(name = "arragementetutgaar")
    public Timestamp getArragementetutgaar() {
        return arragementetutgaar;
    }

    public void setArragementetutgaar(Timestamp arragementetutgaar) {
        this.arragementetutgaar = arragementetutgaar;
    }

    @Override
    public String toString() {
        return "ArrangementBean{" +
                "arrangementid=" + arrangementid +
                ", arrangementnavn='" + arrangementnavn + '\'' +
                ", arrangementbeskrivelse='" + arrangementbeskrivelse + '\'' +
                ", arragementetutgaar=" + arragementetutgaar +
                '}';
    }
}
