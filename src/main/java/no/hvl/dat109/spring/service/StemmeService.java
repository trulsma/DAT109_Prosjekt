package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.repository.StemmeRepository;
import no.hvl.dat109.spring.service.Interfaces.IStemmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StemmeService implements IStemmeService {
    //TODO Implement stemmeservice

    @Autowired
    private StemmeRepository stemmeRepository;
}
