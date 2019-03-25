package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stemmemetode", schema = "prosjekt1")
public class StemmeMetodeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int metodeid;

    private String metodenavn;

    private int metodeparameter;

    @OneToMany(mappedBy = "stemmemetode")
    private List<ArrangementBean> prosjekter;

    public StemmeMetodeBean() {
    }

    public StemmeMetodeBean(String metodenavn, int metodeparameter) {
        this.metodenavn = metodenavn;
        this.metodeparameter = metodeparameter;
    }

    public int getMetodeid() {
        return metodeid;
    }

    public void setMetodeid(int metodeid) {
        this.metodeid = metodeid;
    }

    public String getMetodenavn() {
        return metodenavn;
    }

    public void setMetodenavn(String metodenavn) {
        this.metodenavn = metodenavn;
    }

    public int getMetodeparameter() {
        return metodeparameter;
    }

    public void setMetodeparameter(int metodeparameter) {
        this.metodeparameter = metodeparameter;
    }

    @Override
    public String toString() {
        return "StemmeMetodeBean{" +
                "metodeid=" + metodeid +
                ", metodenavn='" + metodenavn + '\'' +
                ", metodeparameter=" + metodeparameter +
                ", ALLE_PROSJEKTER=" + prosjekter +
                '}';
    }
}
