package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.repository.ProsjektRepository;
import no.hvl.dat109.spring.service.Interfaces.IProsjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProsjektService implements IProsjektService {
    //TODO implement prosjektservice

    @Autowired
    private ProsjektRepository prosjektRepository;
}
