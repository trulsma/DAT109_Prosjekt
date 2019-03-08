package no.hvl.dat109.JPA.Entity;

import javax.persistence.*;

@Entity
@Table(name = "bedrift", schema = "prosjekt1", catalog = "h571718")
public class BedriftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedriftid;

    private String bedriftnavn, bedriftbeskrivelse;

    @Id
    @Column(name = "bedriftid")
    public int getBedriftid() {
        return bedriftid;
    }

    public void setBedriftid(int bedriftid) {
        this.bedriftid = bedriftid;
    }

    @Basic
    @Column(name = "bedriftnavn")
    public String getBedriftnavn() {
        return bedriftnavn;
    }

    public void setBedriftnavn(String bedriftnavn) {
        this.bedriftnavn = bedriftnavn;
    }

    @Basic
    @Column(name = "bedriftbeskrivelse")
    public String getBedriftbeskrivelse() {
        return bedriftbeskrivelse;
    }

    public void setBedriftbeskrivelse(String bedriftbeskrivelse) {
        this.bedriftbeskrivelse = bedriftbeskrivelse;
    }

    @Override
    public String toString() {
        return "BedriftEntity{" +
                "bedriftid=" + bedriftid +
                ", bedriftnavn='" + bedriftnavn + '\'' +
                ", bedriftbeskrivelse='" + bedriftbeskrivelse + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BedriftEntity entity = (BedriftEntity) o;

        if (bedriftid != entity.bedriftid) return false;
        if (bedriftnavn != null ? !bedriftnavn.equals(entity.bedriftnavn) : entity.bedriftnavn != null) return false;
        if (bedriftbeskrivelse != null ? !bedriftbeskrivelse.equals(entity.bedriftbeskrivelse) : entity.bedriftbeskrivelse != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bedriftid;
        result = 31 * result + (bedriftnavn != null ? bedriftnavn.hashCode() : 0);
        result = 31 * result + (bedriftbeskrivelse != null ? bedriftbeskrivelse.hashCode() : 0);
        return result;
    }
}
