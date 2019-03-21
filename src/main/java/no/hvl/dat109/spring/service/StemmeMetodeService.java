package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.StemmeMetodeBean;
import no.hvl.dat109.spring.repository.StemmeMetodeRepository;
import no.hvl.dat109.spring.service.Interfaces.IStemmeMetode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StemmeMetodeService implements IStemmeMetode {

    //Todo implement methods

    @Autowired
    private StemmeMetodeRepository stemmeMetodeRepository;

    @Override
    public String getAlleStemmemetoderAsString() {
        StemmeMetodeBean bean = stemmeMetodeRepository.findById(1).orElse(null);

        if (bean == null) return "Ingen data";
        return bean.toString();
    }

    @Override
    public void addStemmemetode(String metodenavn, int metodeparameter) {
        //Tanken her er at metodenavn kan være "Flervalg eller Stemme/Ikke stemme" og metodeparameter er eventuelt
        //hvor mange radio buttons som skal bli lagt til.
        //Ex: addStemmemetode("Flervalg", 10) -> 10 radio buttons.,
        //Ex: addStemmemetode("Stemme/Ikke stemme", 1) -> QR code med direkte stemming i seg.

        StemmeMetodeBean bean = new StemmeMetodeBean(metodenavn, metodeparameter);
        stemmeMetodeRepository.save(bean);
    }

    @Override
    public void removeStemmemetode(StemmeMetodeBean stemmemetode) {
        stemmeMetodeRepository.delete(stemmemetode);
    }

    @Override
    public void editStemmemetodeNavn(StemmeMetodeBean stemmemetode, String nyttNavn) {
        for(StemmeMetodeBean bean : stemmeMetodeRepository.findAll()){
            if(bean.getMetodeid() == stemmemetode.getMetodeid()){
                bean.setMetodenavn(nyttNavn);
            }
        }
    }

    @Override
    public Iterable<StemmeMetodeBean> getAllStemmemetoder() {
        return stemmeMetodeRepository.findAll();

    }

    @Override
    public StemmeMetodeBean getStemmeMetode(int metodeid) {
        return stemmeMetodeRepository.findById(metodeid).orElse(null);

    }

    @Override
    public StemmeMetodeBean getStemmeMetoderByName(String name) {
        for(StemmeMetodeBean bean : stemmeMetodeRepository.findAll()){
            if(bean.getMetodenavn().equals(name)) return  bean;
        }
        return null;

    }
}
