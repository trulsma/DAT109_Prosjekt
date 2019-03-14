package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.repository.ArrangementRepository;
import no.hvl.dat109.spring.service.Interfaces.IArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArrangementService implements IArrangementService {

    @Autowired
    private ArrangementRepository arrangementRepository;

    @Override
    public String test() {
        arrangementRepository.findAll().forEach(System.out::println);

        return "";
    }
}
