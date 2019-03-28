package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ArrangementdeltagelseBean;
import no.hvl.dat109.spring.repository.ArrangementRepository;
import no.hvl.dat109.spring.repository.ArrangementdeltagelseRepository;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArrangementService implements IArrangementService {

    @Autowired
    private ArrangementRepository arrangementRepository;

    @Autowired
    private ArrangementdeltagelseService deltagelseService;

    @Override
    public String getAllArrangementAsString() {

        ArrangementBean bean = arrangementRepository.findById(1).orElse(null);

        if (bean == null) return "Ingen data";
        return bean.toString();
    }

    @Override
    public List<ArrangementBean> getAllArrangementerNotAttending(int prosjektid) {
        List<ArrangementBean> arrangementer = new ArrayList<>();
        Iterator<ArrangementdeltagelseBean> arrangementdeltagelseIterator = deltagelseService.getAllArrangementdeltagelser().iterator();

        ArrangementdeltagelseBean deltagelse;
        while (arrangementdeltagelseIterator.hasNext()) {
            deltagelse = arrangementdeltagelseIterator.next();

            if (deltagelse.getProsjekt().getProsjektid() != prosjektid)
                arrangementer.add(deltagelse.getArrangement());
        }

        return arrangementer.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void addArrangement(String arrangementnavn, String arrangementbeskrivelse, Date arrangementutgaar) {
        ArrangementBean nyBean = new ArrangementBean(arrangementnavn, arrangementbeskrivelse, arrangementutgaar);
        arrangementRepository.save(nyBean);
    }

    @Override
    public void removeArrangement(ArrangementBean arrangemen) {
        arrangementRepository.deleteById(arrangemen.getArrangementid());
    }

    @Override
    public void updateArrangemntNavn(ArrangementBean arrangement, String nyttNavn) {
        ArrangementBean a = arrangementRepository.findById(arrangement.getArrangementid()).orElse(null);

        if (a == null) return;
        a.setArrangementnavn(nyttNavn);
        arrangementRepository.save(a);
    }

    @Override
    public ArrangementBean getArrangement(int arrangementid) {
        return arrangementRepository.findById(arrangementid).orElse(null);
    }

    @Override
    public ArrangementBean getArrangementByName(String arrangementNavn) {
        ArrangementBean a = null;
        for (ArrangementBean arrangementBean : arrangementRepository.findAll()) {
            if (arrangementBean.getArrangementnavn().equals(arrangementNavn)) {
                a = arrangementBean;
                break;
            }
        }
        return a;
    }

    @Override
    public Iterable<ArrangementBean> getAllArrangement() {
        return arrangementRepository.findAll();
    }
}
