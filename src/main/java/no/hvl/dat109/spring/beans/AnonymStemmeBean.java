package no.hvl.dat109.spring.beans;

import java.io.Serializable;
import java.util.Date;

public class AnonymStemmeBean implements Serializable {
    private int stemmeid;
    private Integer stemmeverdi;
    private Integer prosjektid;
    private Date stemmetidspunkt;

    public int getStemmeid() {
        return stemmeid;
    }

    public Integer getStemmeverdi() {
        return stemmeverdi;
    }

    public Integer getProsjektid() {
        return prosjektid;
    }

    public Date getStemmetidspunkt() {
        return stemmetidspunkt;
    }

    public AnonymStemmeBean(StemmeBean stemmeBean) {
        stemmeid = stemmeBean.getStemmeid();
        stemmeverdi = stemmeBean.getStemmeverdi();
        //prosjektid = stemmeBean.getProsjekt().getProsjektid();
        stemmetidspunkt = stemmeBean.getStemmetidspunkt();
    }
}
