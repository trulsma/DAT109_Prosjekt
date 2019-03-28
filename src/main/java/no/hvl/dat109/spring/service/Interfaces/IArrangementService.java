package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.ArrangementBean;

import java.util.Date;
import java.util.List;

public interface IArrangementService {


    String getAllArrangementAsString();

    List<ArrangementBean> getAllArrangementerNotAttending(int prosjektid);

    void addArrangement(String arrangementnavn, String arrangementbeskrivelse, Date arrangementutgaar);

    void removeArrangement(ArrangementBean arrangement);

    void updateArrangemntNavn(ArrangementBean arrangement, String nyttNavn);

    ArrangementBean getArrangement(int arrangementid);

    ArrangementBean getArrangementByName(String arrangementNavn);

    Iterable<ArrangementBean> getAllArrangement();
}
