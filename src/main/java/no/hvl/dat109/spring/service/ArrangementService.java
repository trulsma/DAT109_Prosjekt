package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.repository.ArrangementRepository;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ArrangementService implements IArrangementService {

    @Autowired
    private ArrangementRepository arrangementRepository;

    @Override
    public String getAllArrangementAsString() {

        ArrangementBean bean = arrangementRepository.findById(1).orElse(null);

        if (bean == null) return "Ingen data";
        return bean.toString();
    }

    @Override
    public void addArrangement(String arrangementnavn, String arrangementbeskrivelse, Date arrangementutgaar) {
        //Todo implment method
    }

    @Override
    public void removeArrangement(ArrangementBean arrangemen) {
        //Todo implment method
        arrangementRepository.deleteById(arrangemen.getArrangementid());
    }

    @Override
    public void updateArrangemntNavn(ArrangementBean arrangement, String nyttNavn) {
       ArrangementBean a = arrangementRepository.findById(arrangement.getArrangementid()).orElse(null);

       a.setArrangementnavn(nyttNavn);

       return;

    }

    @Override
    public ArrangementBean getArrangement(int arrangementid) {
        //Todo implment method
        return null;
    }

    @Override
    public ArrangementBean getArrangementByName(String arrangementNavn) {
        ArrangementBean a = null;
        for(ArrangementBean arrangementBean : arrangementRepository.findAll()){
            if(arrangementBean.equals(arrangementNavn)){
                a = arrangementBean;
            }
        }
        return a;
    }

    @Override
    public Iterable<ArrangementBean> getAllArrangement() {
        //Todo implment method
        return null;
    }
}
