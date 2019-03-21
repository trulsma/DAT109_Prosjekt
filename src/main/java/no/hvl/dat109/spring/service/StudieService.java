package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.StudieBean;
import no.hvl.dat109.spring.repository.StudieRepository;
import no.hvl.dat109.spring.service.Interfaces.IStudieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudieService implements IStudieService {

    @Autowired
    private StudieRepository studieRepository;

    @Override
    public String test() {
        studieRepository.findAll().forEach(System.out::println);
        return "";
    }

    @Override
    public StudieBean getStudieById(int id) {
        return studieRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<StudieBean> getAllStudier() {
        return null;
    }
}
