package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ArrangementdeltagelseBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.repository.ArrangementdeltagelseRepository;
import no.hvl.dat109.spring.service.Interfaces.IArrangementdeltagelseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ArrangementdeltagelseService implements IArrangementdeltagelseService {


    @Autowired
    private ArrangementdeltagelseRepository arrangementdeltagelseRepository;

    @Override
    public String test() {

        arrangementdeltagelseRepository.findAll().forEach(System.out::println);

        return "index";
    }

    @Override
    public void addArrangementDeltagelse(ArrangementBean arrangement, ProsjektBean prosjek) {
        //Todo implement this
    }

    @Override
    public void removeArrangementDeltagelse(ArrangementdeltagelseBean deltagelse) {
        //Todo implement this
    }

    @Override
    public ArrangementdeltagelseBean getArrangementDeltagelse(int id) {
        //Todo implement this
        return null;
    }

    @Override
    public List<ProsjektBean> getAllProsjektFromArrangement(ArrangementBean arrangement) {
        //Todo implement this
        return null;
    }
}
