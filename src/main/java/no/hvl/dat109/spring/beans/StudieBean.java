package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "studie", schema = "prosjekt1")
public class StudieBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studieid;

    private String studienavn, studiebeskrivelse;

    @ManyToOne
    @JoinColumn(name = "studiekategori")
    private KategoriBean studiekategori;

    @OneToMany(mappedBy = "studieKategori")
    private List<ProsjektBean> prosjekter;


    public StudieBean() {
    }

    public StudieBean(String studienavn, String beskrivelse) {
        this.studienavn = studienavn;
        this.studiebeskrivelse = beskrivelse;
    }

    public int getStudieid() {
        return studieid;
    }

    public void setStudieid(int studieid) {
        this.studieid = studieid;
    }

    public String getStudienavn() {
        return studienavn;
    }

    public void setStudienavn(String studienavn) {
        this.studienavn = studienavn;
    }

    public String getStudiebeskrivelse() {
        return studiebeskrivelse;
    }

    public void setStudiebeskrivelse(String beskrivelse) {
        this.studiebeskrivelse = beskrivelse;
    }

    public KategoriBean getStudiekategori() {
        return studiekategori;
    }

    public void setStudiekategori(KategoriBean studiekategori) {
        this.studiekategori = studiekategori;
    }

    public List<ProsjektBean> getProsjekter() {
        return prosjekter;
    }

    public void setProsjekter(List<ProsjektBean> prosjekter) {
        this.prosjekter = prosjekter;
    }

    @Override
    public String toString() {
        return "StudieBean{" +
                "studieid=" + studieid +
                ", studienavn='" + studienavn + '\'' +
                ", beskrivelse='" + studiebeskrivelse + '\'' +
                ", studiekategori=" + studiekategori +
                '}';
    }
}
