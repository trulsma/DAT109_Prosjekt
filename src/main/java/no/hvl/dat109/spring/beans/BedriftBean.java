package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bedrift", schema = "prosjekt1")
public class BedriftBean implements Serializable {

    //Todo can benefit from new toString, otherwise done

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedriftid;

    private String bedriftnavn;
    private String bedriftbeskrivelse;

    public BedriftBean() {
    }

    public BedriftBean(String bedriftnavn, String bedriftbeskrivelse) {
        this.bedriftnavn = bedriftnavn;
        this.bedriftbeskrivelse = bedriftbeskrivelse;
    }

    public int getBedriftid() {
        return bedriftid;
    }

    public void setBedriftid(int bedriftid) {
        this.bedriftid = bedriftid;
    }

    public String getBedriftnavn() {
        return bedriftnavn;
    }

    public void setBedriftnavn(String bedriftnavn) {
        this.bedriftnavn = bedriftnavn;
    }

    public String getBedriftbeskrivelse() {
        return bedriftbeskrivelse;
    }

    public void setBedriftbeskrivelse(String bedriftbeskrivelse) {
        this.bedriftbeskrivelse = bedriftbeskrivelse;
    }

    @Override
    public String toString() {
        return "Bedrift{" +
                "bedriftid=" + bedriftid +
                ", bedriftnavn='" + bedriftnavn + '\'' +
                ", bedriftbeskrivelse='" + bedriftbeskrivelse + '\'' +
                '}';
    }
}
