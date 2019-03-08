package JPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BedriftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedriftid;

    private String bedriftnavn, bedriftbeskrivelse;

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
        return "BedriftEntity{" +
                "bedriftid=" + bedriftid +
                ", bedriftnavn='" + bedriftnavn + '\'' +
                ", bedriftbeskrivelse='" + bedriftbeskrivelse + '\'' +
                '}';
    }
}
