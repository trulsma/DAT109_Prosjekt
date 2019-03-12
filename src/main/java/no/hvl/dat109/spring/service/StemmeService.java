package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.StemmeBean;
import no.hvl.dat109.spring.repository.StemmeRepository;
import no.hvl.dat109.spring.service.Interfaces.IStemmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StemmeService implements IStemmeService {
    //TODO Implement stemmeservice

    @Autowired
    private StemmeRepository stemmeRepository;

    @Override
    public void addStemme(StemmeBean stemme) {
        stemmeRepository.save(stemme);
    }

    @Override
    public Integer getTotalStemmeverdi(String prosjektNavn) {
        return null;
    }

    @Override
    public StemmeBean getStemmeById(int id) {
        return stemmeRepository.findById(id).orElse(null);
    }


}
