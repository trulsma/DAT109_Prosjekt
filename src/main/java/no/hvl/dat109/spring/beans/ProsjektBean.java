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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prosjektkategori")
    private KategoriBean prosjektkategori;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sammarbeidsbedrift")
    private BedriftBean sammarbeidsbedrift;

    @OneToMany(mappedBy = "prosjekt")
    private List<StemmeBean> stemmer;


    public ProsjektBean() {
    }

    public ProsjektBean(String prosjektnavn, String prosjektbeskrivelse, BedriftBean sammarbeidsbedrift) {
        this.prosjektnavn = prosjektnavn;
        this.prosjektbeskrivelse = prosjektbeskrivelse;
        this.sammarbeidsbedrift = sammarbeidsbedrift;
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

    public BedriftBean getSammarbeidsbedrift() {
        return sammarbeidsbedrift;
    }

    public void setSammarbeidsbedrift(BedriftBean sammarbeidsbedrift) {
        this.sammarbeidsbedrift = sammarbeidsbedrift;
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

    public List<StemmeBean> getStemmer() {
        return stemmer;
    }

    public void setStemmer(List<StemmeBean> stemmer) {
        this.stemmer = stemmer;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public KategoriBean getProsjektkategori() {
        return prosjektkategori;
    }

    public void setProsjektkategori(KategoriBean prosjektkategori) {
        this.prosjektkategori = prosjektkategori;
    }

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

    @Override
    public String toString() {
        return "ProsjektBean{" +
                "prosjektid=" + prosjektid +
                ", prosjektnavn='" + prosjektnavn + '\'' +
                ", prosjektbeskrivelse='" + prosjektbeskrivelse + '\'' +
                ", shortenedurl='" + shortenedurl + '\'' +
                ", qrimagepath='" + qrimagepath + '\'' +
                ", sammarbeidsbedrift=" + sammarbeidsbedrift +
                ", stemmer=" + stemmer +
                '}';
    }
}
