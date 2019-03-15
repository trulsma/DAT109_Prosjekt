package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.repository.ArrangementdeltagelseRepository;
import no.hvl.dat109.spring.service.Interfaces.IArrangementdeltagelseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrangementdeltagelseService implements IArrangementdeltagelseService {


    @Autowired
    private ArrangementdeltagelseRepository arrangementdeltagelseRepository;

    @Override
    public String test() {

        arrangementdeltagelseRepository.findAll().forEach(System.out::println);

        return "";
    }
}
