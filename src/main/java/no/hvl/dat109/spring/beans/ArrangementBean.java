package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "arrangement", schema = "prosjekt1")
public class ArrangementBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int arrangementid;
    private String arrangementnavn;
    private String arrangementbeskrivelse;
    private Date arragementetutgaar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrangementstemmemetode")
    private StemmeMetodeBean stemmemetode;


    @OneToMany(mappedBy = "arrangement")
    private List<ArrangementdeltagelseBean> deltagelser;

    public ArrangementBean() {
    }

    public ArrangementBean(String arrangementnavn, String arrangementbeskrivelse, Date arragementetutgaar) {
        this.arrangementnavn = arrangementnavn;
        this.arrangementbeskrivelse = arrangementbeskrivelse;
        this.arragementetutgaar = arragementetutgaar;
    }

    public List<ArrangementdeltagelseBean> getDeltagelser() {
        return deltagelser;
    }

    public void setDeltagelser(List<ArrangementdeltagelseBean> deltagelser) {
        this.deltagelser = deltagelser;
    }

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

    public StemmeMetodeBean getStemmemetode() {
        return stemmemetode;
    }

    public void setStemmemetode(StemmeMetodeBean stemmemetode) {
        this.stemmemetode = stemmemetode;
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
    public Date getArragementetutgaar() {
        return arragementetutgaar;
    }

    public void setArragementetutgaar(Date arragementetutgaar) {
        this.arragementetutgaar = arragementetutgaar;
    }

    @Override
    public String toString() {
        return "ArrangementBean{" +
                "ARRANGEMENT_WITH_ID=" + arrangementid +
                ", arrangementnavn='" + arrangementnavn + '\'' +
                ", arrangementbeskrivelse='" + arrangementbeskrivelse + '\'' +
                ", arragementetutgaar=" + arragementetutgaar +
                '}';
    }
}
