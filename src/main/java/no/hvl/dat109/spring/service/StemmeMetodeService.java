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
        //Todo implement method

        //Tanken her er at metodenavn kan være "Flervalg eller Stemme/Ikke stemme" og metodeparameter er eventuelt
        //hvor mange radio buttons som skal bli lagt til.
        //Ex: addStemmemetode("Flervalg", 10) -> 10 radio buttons.,
        //Ex: addStemmemetode("Stemme/Ikke stemme", 1) -> QR code med direkte stemming i seg.
    }

    @Override
    public void removeStemmemetode(StemmeMetodeBean stemmemetode) {
        //Todo implement method
    }

    @Override
    public void editStemmemetodeNavn(StemmeMetodeBean stemmemetode, String nyttNavn) {
        //Todo implement method
    }

    @Override
    public Iterable<StemmeMetodeBean> getAllStemmemetoder() {
        //Todo implement method
        return null;

    }

    @Override
    public StemmeMetodeBean getStemmeMetode(int metodeid) {
        //Todo implement method
        return null;

    }

    @Override
    public StemmeMetodeBean getStemmeMetoderByName(String name) {
        //Todo implement method
        return null;

    }
}
