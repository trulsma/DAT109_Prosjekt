package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.BedriftBean;

public interface IBedriftService {

    //TODO
    String getAll();

    BedriftBean getBedriftById(int id);

    void addBedrift(String bedriftnavn, String bedriftbeskrivelse);

    boolean exists(String name);

    boolean exists(int id);

}
