package no.hvl.dat109.spring.beans;

import java.io.Serializable;
import java.util.Date;

public class ResultatStemmeBean implements Serializable {
    private Double stemmeverdi;
    private Integer antall;
    private Integer prosjektid;
    private Date stemmetidspunkt;

    public ResultatStemmeBean(Double stemmeverdi, Integer antall, Integer prosjektid, Date stemmetidspunkt) {
        this.stemmeverdi = stemmeverdi;
        this.antall = antall;
        this.prosjektid = prosjektid;
        this.stemmetidspunkt = stemmetidspunkt;
    }

    public Double getStemmeverdi() {
        return stemmeverdi;
    }

    public Integer getProsjektid() {
        return prosjektid;
    }

    public Date getStemmetidspunkt() {
        return stemmetidspunkt;
    }

    public Integer getAntall() { return antall; }
}
