package no.hvl.dat109.spring.service.Interfaces;


import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ArrangementdeltagelseBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.repository.ArrangementdeltagelseRepository;

import java.util.List;

public interface IArrangementdeltagelseService {

    String test();

    void addArrangementDeltagelse(ArrangementBean arrangement, ProsjektBean prosjek);

    void removeArrangementDeltagelse(ArrangementdeltagelseBean deltagelse);

    ArrangementdeltagelseBean getArrangementDeltagelse(int id);

    /**
     * Metode for å finne alle prosjekt i et arrangement
     *
     * @param arrangement arrangementet du vil finne prosjekter til
     * @return liste med alle prosjekter på dette arrangementet
     */
    List<ProsjektBean> getAllProsjektFromArrangement(ArrangementBean arrangement);


}
