package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "prosjekt", schema = "prosjekt1")
public class ProsjektBean implements Serializable {

    //Todo can benefit from new toString, otherwise done

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjektid;
    private String prosjektnavn;
    private String prosjektbeskrivelse;
    private String shortenedurl;
    private String qrimagepath;
    private String pictureurl;
    private String backgroundurl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studie")
    private StudieBean studieKategori;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sammarbeidsbedrift")
    private BedriftBean sammarbeidsbedrift;

    @OneToMany(mappedBy = "prosjekt")
    private List<ArrangementdeltagelseBean> arragementdeltagelser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prosjektuser")
    private UsersBean prosjektEiger;


    public ProsjektBean() {
    }

    public ProsjektBean(String prosjektnavn, String prosjektbeskrivelse, BedriftBean sammarbeidsbedrift) {
        this.prosjektnavn = prosjektnavn;
        this.prosjektbeskrivelse = prosjektbeskrivelse;
        this.sammarbeidsbedrift = sammarbeidsbedrift;
        this.pictureurl = "images/default-icon.png";
    }

    public ProsjektBean(String prosjektnavn, String prosjektbeskrivelse, BedriftBean sammarbeidsbedrift, StudieBean studieKategori, UsersBean prosjektEiger) {
        this.prosjektnavn = prosjektnavn;
        this.prosjektbeskrivelse = prosjektbeskrivelse;
        this.sammarbeidsbedrift = sammarbeidsbedrift;
        this.studieKategori = studieKategori;
        this.prosjektEiger = prosjektEiger;
        this.pictureurl = "images/default-icon.png";
    }

    public int getProsjektid() {
        return prosjektid;
    }

    public void setProsjektid(int prosjektid) {
        this.prosjektid = prosjektid;
    }

    public String getProsjektnavn() {
        return prosjektnavn;
    }

    public void setProsjektnavn(String prosjektnavn) {
        this.prosjektnavn = prosjektnavn;
    }

    public String getProsjektbeskrivelse() {
        return prosjektbeskrivelse;
    }

    public void setProsjektbeskrivelse(String prosjektbeskrivelse) {
        this.prosjektbeskrivelse = prosjektbeskrivelse;
    }

    public String getShortenedurl() {
        return shortenedurl;
    }

    public void setShortenedurl(String shortenedurl) {
        this.shortenedurl = shortenedurl;
    }

    public String getQrimagepath() {
        return qrimagepath;
    }

    public void setQrimagepath(String qrimagepath) {
        this.qrimagepath = qrimagepath;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getBackgroundurl() {
        return backgroundurl;
    }

    public void setBackgroundurl(String backgroundurl) {
        this.backgroundurl = backgroundurl;
    }

    public StudieBean getStudieKategori() {
        return studieKategori;
    }

    public void setStudieKategori(StudieBean studieKategori) {
        this.studieKategori = studieKategori;
    }

    public BedriftBean getSammarbeidsbedrift() {
        return sammarbeidsbedrift;
    }

    public void setSammarbeidsbedrift(BedriftBean sammarbeidsbedrift) {
        this.sammarbeidsbedrift = sammarbeidsbedrift;
    }

    public List<ArrangementdeltagelseBean> getArragementdeltagelser() {
        return arragementdeltagelser;
    }

    public void setArragementdeltagelser(List<ArrangementdeltagelseBean> arragementdeltagelser) {
        this.arragementdeltagelser = arragementdeltagelser;
    }

    public UsersBean getProsjektEiger() {
        return prosjektEiger;
    }

    public void setProsjektEiger(UsersBean prosjektEiger) {
        this.prosjektEiger = prosjektEiger;
    }

    public boolean erEigerAvProsjekt(UsersBean user) {
        if (user == null || this.prosjektEiger == null) return false;
        return this.prosjektEiger.getUserid() == user.getUserid();
    }
    /*
    public Integer getStemmerMedVerdi() {
        Integer sum = 0;
        for (StemmeBean stemme : stemmer) {
            sum += stemme.getStemmeverdi();
        }
        return sum;
    }

    public Double getStemmeGjennomsnitt() {
        return new Double(getStemmerMedVerdi()) / stemmer.size();
    }
    */

    @Override
    public String toString() {
        return "ProsjektBean{" +
                "prosjektid=" + prosjektid +
                ", prosjektnavn='" + prosjektnavn + '\'' +
                ", prosjektbeskrivelse='" + prosjektbeskrivelse + '\'' +
                ", shortenedurl='" + shortenedurl + '\'' +
                ", qrimagepath='" + qrimagepath + '\'' +
                ", sammarbeidsbedrift=" + sammarbeidsbedrift +
                '}';
    }
}
