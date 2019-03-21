package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.ArrangementdeltagelseBean;
import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.repository.ArrangementdeltagelseRepository;
import no.hvl.dat109.spring.repository.BedriftRepository;
import no.hvl.dat109.spring.repository.ProsjektRepository;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProsjektService implements IProsjektService {
    //TODO implement prosjektservice

    @Autowired
    private ProsjektRepository prosjektRepository;

    @Autowired
    private ArrangementdeltagelseService deltagelseService;

    @Override
    public ProsjektBean getProsjektById(int id) {
        return prosjektRepository.findById(id).orElse(null);
    }

    @Override
    public void addProsjekt(ProsjektBean prosjekt) {
        prosjektRepository.save(prosjekt);
    }

    @Override
    public boolean exists(String name) {
        for (ProsjektBean prosjektBean : prosjektRepository.findAll()) {
            if (prosjektBean.getProsjektnavn().equals(name)) return true;
        }
        return false;
    }

    public ProsjektBean getProsjektByName(String name) {
        for (ProsjektBean prosjektBean : prosjektRepository.findAll()) {
            if (prosjektBean.getProsjektnavn().equals(name)) return prosjektBean;
        }
        return null;
    }

    @Override
    public void updateProsjekt(ProsjektBean prosjekt) {
        prosjektRepository.save(prosjekt);
    }

    @Override
    public void removeProject(ProsjektBean prosjekt) {
        deltagelseService.removeProsjektFromDeltagelse(prosjekt);
        prosjektRepository.delete(prosjekt);
    }

    @Override
    public void removeAllProjects() {
        prosjektRepository.deleteAll();
    }

    @Override
    public Iterable<ProsjektBean> getAlleProsjekter() {
        return prosjektRepository.findAll();
    }
}
