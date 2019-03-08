package no.hvl.dat109.JPA.Entity;

import javax.persistence.*;

@Entity
@Table(name = "prosjekt", schema = "prosjekt1", catalog = "h571718")
public class ProsjektEntity {
    private int prosjektid;
    private String prosjektnavn;
    private String prosjektbeskrivelse;
    private String qrcodeurl;

    @Id
    @Column(name = "prosjektid")
    public int getProsjektid() {
        return prosjektid;
    }

    public void setProsjektid(int prosjektid) {
        this.prosjektid = prosjektid;
    }

    @Basic
    @Column(name = "prosjektnavn")
    public String getProsjektnavn() {
        return prosjektnavn;
    }

    public void setProsjektnavn(String prosjektnavn) {
        this.prosjektnavn = prosjektnavn;
    }

    @Basic
    @Column(name = "prosjektbeskrivelse")
    public String getProsjektbeskrivelse() {
        return prosjektbeskrivelse;
    }

    public void setProsjektbeskrivelse(String prosjektbeskrivelse) {
        this.prosjektbeskrivelse = prosjektbeskrivelse;
    }

    @Basic
    @Column(name = "qrcodeurl")
    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProsjektEntity that = (ProsjektEntity) o;

        if (prosjektid != that.prosjektid) return false;
        if (prosjektnavn != null ? !prosjektnavn.equals(that.prosjektnavn) : that.prosjektnavn != null) return false;
        if (prosjektbeskrivelse != null ? !prosjektbeskrivelse.equals(that.prosjektbeskrivelse) : that.prosjektbeskrivelse != null)
            return false;
        if (qrcodeurl != null ? !qrcodeurl.equals(that.qrcodeurl) : that.qrcodeurl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prosjektid;
        result = 31 * result + (prosjektnavn != null ? prosjektnavn.hashCode() : 0);
        result = 31 * result + (prosjektbeskrivelse != null ? prosjektbeskrivelse.hashCode() : 0);
        result = 31 * result + (qrcodeurl != null ? qrcodeurl.hashCode() : 0);
        return result;
    }
}
