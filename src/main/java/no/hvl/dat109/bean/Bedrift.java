package no.hvl.dat109.bean;

import javax.persistence.*;

@Entity
@Table(name = "bedrift", schema = "prosjekt1")
public class Bedrift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedriftid;

    private String bedriftnavn;
    private String bedriftbeskrivelse;


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
