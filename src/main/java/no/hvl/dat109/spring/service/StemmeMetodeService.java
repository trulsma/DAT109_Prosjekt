package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.StemmeMetodeBean;
import no.hvl.dat109.spring.repository.StemmeMetodeRepository;
import no.hvl.dat109.spring.service.Interfaces.IStemmeMetode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StemmeMetodeService implements IStemmeMetode {

    @Autowired
    private StemmeMetodeRepository stemmeMetodeRepository;

    @Override
    public String test() {
        StemmeMetodeBean bean = stemmeMetodeRepository.findById(1).orElse(null);

        if (bean == null) return "Ingen datas";
        return bean.toString();
    }
}
