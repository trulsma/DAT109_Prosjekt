package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stemme", schema = "prosjekt1")
public class StemmeBean implements Serializable {

    //Todo can benefit from new toString, otherwise done

    private int stemmeid;
    private String epost;
    private Integer stemmeverdi;

    /*
    @OneToMany
    @JoinColumn(name = "prosjektid")
    private ProsjektBean prosjekt;
    */

    public StemmeBean() {

    }

    public StemmeBean(String epost, int stemmeverdi) {
        this.epost = epost;
        this.stemmeverdi = stemmeverdi;
    }

    @Id
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
