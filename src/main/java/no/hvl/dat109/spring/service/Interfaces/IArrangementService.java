package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.ArrangementBean;

import java.util.Date;

public interface IArrangementService {


    String getAllArrangementAsString();

    void addArrangement(String arrangementnavn, String arrangementbeskrivelse, Date arrangementutgaar);

    void removeArrangement(ArrangementBean arrangement);

    void updateArrangemntNavn(ArrangementBean arrangement, String nyttNavn);

    ArrangementBean getArrangement(int arrangementid);

    ArrangementBean getArrangementByName(String arrangementNavn);

    Iterable<ArrangementBean> getAllArrangement();
}
