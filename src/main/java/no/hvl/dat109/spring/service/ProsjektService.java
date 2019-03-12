package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.repository.ProsjektRepository;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProsjektService implements IProsjektService {
    //TODO implement prosjektservice

    @Autowired
    private ProsjektRepository prosjektRepository;

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
}