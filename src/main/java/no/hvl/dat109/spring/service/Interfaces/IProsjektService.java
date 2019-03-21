package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.ProsjektBean;

public interface IProsjektService {

    ProsjektBean getProsjektById(int id);

    void addProsjekt(ProsjektBean prosjekt);

    boolean exists(String name);

    ProsjektBean getProsjektByName(String name);

    Iterable<ProsjektBean> getAlleProsjekter();

    void updateProsjekt(ProsjektBean prosjekt);

    void removeProject(ProsjektBean prosjekt);

    void removeAllProjects();

}
