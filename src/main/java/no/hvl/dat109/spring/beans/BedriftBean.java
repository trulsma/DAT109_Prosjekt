package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "bedrift", schema = "prosjekt1")
public class BedriftBean implements Serializable {

    //Todo can benefit from new toString, otherwise done

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedriftid;

    private String bedriftnavn;
    private String bedriftbeskrivelse;

    @OneToMany(mappedBy = "sammarbeidsbedrift")
    private List<ProsjektBean> prosjekter;

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

    public List<ProsjektBean> getProsjekter() {
        return prosjekter;
    }

    public void setProsjekter(List<ProsjektBean> prosjekter) {
        this.prosjekter = prosjekter;
    }

    @Override
    public String toString() {
        return "Bedrift{" +
                "BEDRIFT_WITH_ID=" + bedriftid +
                ", bedriftnavn='" + bedriftnavn + '\'' +
                ", bedriftbeskrivelse='" + bedriftbeskrivelse + '\'' +
                '}';
    }
}
