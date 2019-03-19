package no.hvl.dat109.spring.beans;

import java.io.Serializable;

public class ProsjektMedStemmerBean implements Serializable {
    private int prosjektid;
    private String prosjektnavn;
    private String prosjektbeskrivelse;
    private int antallStemmer;

    private double gjennomsnittVerdi;

    public double getGjennomsnittVerdi() {
        return gjennomsnittVerdi;
    }

    public int getProsjektid() {
        return prosjektid;
    }

    public String getProsjektnavn() {
        return prosjektnavn;
    }

    public String getProsjektbeskrivelse() {
        return prosjektbeskrivelse;
    }

    public int getAntallStemmer() {
        return antallStemmer;
    }

    public ProsjektMedStemmerBean(int prosjektid, String prosjektnavn, String prosjektbeskrivelse, int antallStemmer, double gjennomsnittVerdi) {
        this.prosjektid = prosjektid;
        this.prosjektnavn = prosjektnavn;
        this.prosjektbeskrivelse = prosjektbeskrivelse;
        this.antallStemmer = antallStemmer;
        this.gjennomsnittVerdi = gjennomsnittVerdi;
    }
}

