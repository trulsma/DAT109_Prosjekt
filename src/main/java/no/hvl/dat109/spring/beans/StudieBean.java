package no.hvl.dat109.spring.beans;

import javax.persistence.*;

@Entity
@Table(name = "studie", schema = "prosjekt1")
public class StudieBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studieid;

    private String studienavn, beskrivelse;

    @ManyToOne
    @JoinColumn(name = "studiekategori")
    private KategoriBean studiekategori;

    public StudieBean() {
    }

    public StudieBean(String studienavn, String beskrivelse) {
        this.studienavn = studienavn;
        this.beskrivelse = beskrivelse;
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

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public KategoriBean getStudiekategori() {
        return studiekategori;
    }

    public void setStudiekategori(KategoriBean studiekategori) {
        this.studiekategori = studiekategori;
    }

    @Override
    public String toString() {
        return "StudieBean{" +
                "studieid=" + studieid +
                ", studienavn='" + studienavn + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", studiekategori=" + studiekategori +
                '}';
    }
}
