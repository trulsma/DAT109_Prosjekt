package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.beans.UsersBean;
import org.springframework.web.multipart.MultipartFile;

public interface IProsjektService {

    ProsjektBean getProsjektById(int id);

    void addProsjekt(ProsjektBean prosjekt);

    boolean exists(String name);

    ProsjektBean getProsjektByName(String name);

    Iterable<ProsjektBean> getAlleProsjekter();

    void updateProsjekt(ProsjektBean prosjekt);

    void removeAllProjects();

    void removeProject(ProsjektBean prosjekt);

    ProsjektBean getProsjektFromOwner(UsersBean user);

    void changeNameOfProject(ProsjektBean prosjekt, String newname);

    void changeBeskrivelse(ProsjektBean prosjekt, String beskrivelse);

    void updatePicturePath(ProsjektBean prosjekt, String path);

    void updateBackgroundPath(ProsjektBean prosjekt, String path);

    String createLogo(MultipartFile logo, ProsjektBean prosjekt);

    String createBackground(MultipartFile background, ProsjektBean prosjekt);
}