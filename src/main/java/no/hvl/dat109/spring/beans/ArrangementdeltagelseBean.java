package no.hvl.dat109.spring.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "arrangementdeltagelse", schema = "prosjekt1", catalog = "h571718")
public class ArrangementdeltagelseBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deltagelseid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrangement")
    private ArrangementBean arrangement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prosjekt")
    private ProsjektBean prosjekt;

    @OneToMany(mappedBy = "prosjekt")
    private List<StemmeBean> stemmer;

    public ArrangementdeltagelseBean(){}

    public ArrangementdeltagelseBean(ArrangementBean arrangement, ProsjektBean prosjekt) {
        this.arrangement = arrangement;
        this.prosjekt = prosjekt;
    }

    public List<StemmeBean> getStemmer() {
        return stemmer;
    }

    public void setStemmer(List<StemmeBean> stemmer) {
        this.stemmer = stemmer;
    }

    public ArrangementBean getArrangement() {
        return arrangement;
    }

    public void setArrangement(ArrangementBean arrangement) {
        this.arrangement = arrangement;
    }

    public ProsjektBean getProsjekt() {
        return prosjekt;
    }

    public void setProsjekt(ProsjektBean prosjekt) {
        this.prosjekt = prosjekt;
    }

    public int getDeltagelseid() {
        return deltagelseid;
    }

    public void setDeltagelseid(int deltagelseid) {
        this.deltagelseid = deltagelseid;
    }

    @Override
    public String toString() {
        return "ArrangementdeltagelseBean{" +
                "deltagelseid=" + deltagelseid +
                ", arrangement=" + arrangement +
                ", prosjekt=" + prosjekt +
                '}';
    }
}
