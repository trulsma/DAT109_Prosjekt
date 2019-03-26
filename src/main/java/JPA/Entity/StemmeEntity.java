package JPA.Entity;

import javax.persistence.*;

@Entity
@Table(name = "stemme", schema = "prosjekt1", catalog = "h571718")
public class StemmeEntity {
    private int stemmeid;
    private String epost;
    private Integer stemmeverdi;

    @Id
    @Column(name = "stemmeid")
    public int getStemmeid() {
        return stemmeid;
    }

    public void setStemmeid(int stemmeid) {
        this.stemmeid = stemmeid;
    }

    @Basic
    @Column(name = "epost")
    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    @Basic
    @Column(name = "stemmeverdi")
    public Integer getStemmeverdi() {
        return stemmeverdi;
    }

    public void setStemmeverdi(Integer stemmeverdi) {
        this.stemmeverdi = stemmeverdi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StemmeEntity that = (StemmeEntity) o;

        if (stemmeid != that.stemmeid) return false;
        if (epost != null ? !epost.equals(that.epost) : that.epost != null) return false;
        if (stemmeverdi != null ? !stemmeverdi.equals(that.stemmeverdi) : that.stemmeverdi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stemmeid;
        result = 31 * result + (epost != null ? epost.hashCode() : 0);
        result = 31 * result + (stemmeverdi != null ? stemmeverdi.hashCode() : 0);
        return result;
    }
}
