package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.StemmeMetodeBean;

public interface IStemmeMetodeService {

    //Todo create service methods for stemmemetoder

    String getAlleStemmemetoderAsString();

    void addStemmemetode(String metodenavn, int metodeparameter);

    void removeStemmemetode(StemmeMetodeBean stemmemetode);

    void editStemmemetodeNavn(StemmeMetodeBean stemmemetode, String nyttNavn);

    Iterable<StemmeMetodeBean> getAllStemmemetoder();

    StemmeMetodeBean getStemmeMetode(int metodeid);

    StemmeMetodeBean getStemmeMetoderByName(String name);




}
