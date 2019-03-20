package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stemme", schema = "prosjekt1")
public class StemmeBean implements Serializable {

    //Todo can benefit from new toString, otherwise done

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stemmeid;

    private String epost;
    private Integer stemmeverdi;
    //private Integer prosjektid;
    private Date stemmetidspunkt;

    @ManyToOne
    @JoinColumn(name = "arrangementdeltagelseid")
    private ArrangementdeltagelseBean prosjekt;

    public StemmeBean() {

    }

    public StemmeBean(ArrangementdeltagelseBean prosjekt, String epost, int stemmeverdi) {
        this.prosjekt = prosjekt;
        this.epost = epost;
        this.stemmeverdi = stemmeverdi;
        stemmetidspunkt = new Date();
    }

    public ArrangementdeltagelseBean getProsjekt() {
        return prosjekt;
    }

    @Column(name = "stemmeid")
    public int getStemmeid() {
        return stemmeid;
    }

    public void setStemmeid(int stemmeid) {
        this.stemmeid = stemmeid;
    }

    @Basic
    @Column(name = "epost")
    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public Date getStemmetidspunkt() {
        return stemmetidspunkt;
    }

    public void setStemmetidspunkt(Date stemmetidspunkt) {
        this.stemmetidspunkt = stemmetidspunkt;
    }

    @Basic
    @Column(name = "stemmeverdi")
    public Integer getStemmeverdi() {
        return stemmeverdi;
    }

    public void setStemmeverdi(Integer stemmeverdi) {
        this.stemmeverdi = stemmeverdi;
    }

    @Override
    public String toString() {
        return "StemmeBean{" +
                "stemmeid=" + stemmeid +
                ", epost='" + epost + '\'' +
                ", stemmeverdi=" + stemmeverdi +
                '}';
    }
}
