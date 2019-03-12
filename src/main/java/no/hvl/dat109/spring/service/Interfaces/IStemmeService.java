package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.StemmeBean;

import java.util.List;

public interface IStemmeService {
    //TODO lag stemme interface

    void addStemme(StemmeBean stemmeBean);

    Integer getTotalStemmeverdi(String prosjektNavn);





}
