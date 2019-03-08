package no.hvl.dat109.service;

import no.hvl.dat109.bean.Bedrift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.hvl.dat109.repository.BedriftRepository;

@Service
public class BedriftService implements IBedriftService {

    @Autowired
    private BedriftRepository repo;


    @Override
    public String test() {
        Bedrift bedrift = repo.findById(1).orElse(null);
        System.out.println(bedrift.getBedriftbeskrivelse());

        return bedrift.toString();
    }
}
