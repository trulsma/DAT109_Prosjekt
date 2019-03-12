package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.StemmeBean;
import no.hvl.dat109.spring.repository.StemmeRepository;
import no.hvl.dat109.spring.service.Interfaces.IStemmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StemmeService implements IStemmeService {
    //TODO Implement stemmeservice

    @Autowired
    private StemmeRepository stemmeRepository;


    @Override
    public void addStemme(String epost, Integer stemmeverdi) {
        stemmeRepository.save(new StemmeBean(epost, stemmeverdi));
    }

    @Override
    public List<StemmeBean> getStemmerIEnBedrift(int bedriftId) {
        return null;
    }
}
