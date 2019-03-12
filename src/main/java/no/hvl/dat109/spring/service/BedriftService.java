package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.BedriftBean;
import no.hvl.dat109.spring.repository.BedriftRepository;
import no.hvl.dat109.spring.service.Interfaces.IBedriftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedriftService implements IBedriftService {

    //Todo create services needed for controller

    @Autowired
    private BedriftRepository bedriftRepository;

    @Override
    public String getAll() {
        StringBuilder builder = new StringBuilder();
        bedriftRepository.findAll().iterator().forEachRemaining(repository -> builder.append(repository).append("\n"));
        return builder.toString().equals("") ? "Ingen bedrift" : builder.toString();
    }

    @Override
    public Iterable<BedriftBean> getAlleBedrifter() {
        return bedriftRepository.findAll();
    }

    @Override
    public BedriftBean getBedriftById(int id) {
        return bedriftRepository.findById(id).orElse(null);
    }

    @Override
    public void addBedrift(BedriftBean bedrift) {
        bedriftRepository.save(bedrift);
    }

    @Override
    public boolean exists(String name) {
        for (BedriftBean bedriftBean : bedriftRepository.findAll()) {
            if (bedriftBean.getBedriftnavn().equals(name)) return true;
        }
        return false;
    }

    @Override
    public boolean exists(int id) {
        BedriftBean bean = bedriftRepository.findById(id).orElse(null);
        return bean != null;
    }
}
