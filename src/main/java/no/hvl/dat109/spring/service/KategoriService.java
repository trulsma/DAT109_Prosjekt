package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.KategoriBean;
import no.hvl.dat109.spring.beans.ProsjektBean;
import no.hvl.dat109.spring.repository.KategoriRepository;
import no.hvl.dat109.spring.service.Interfaces.IKategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriService implements IKategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    @Override
    public String getAllKategorierAsString() {
        KategoriBean kategoriBean = kategoriRepository.findById(1).orElse(null);

        if (kategoriBean == null) return "Ingen data";
        return kategoriBean.toString();
    }

    @Override
    public Iterable<KategoriBean> getAllKategorier() {
        //Todo Implement this
        return null;
    }

    @Override
    public List<ProsjektBean> getAllProsjektWithKategori(int kategoriid) {
        KategoriBean kategoriBean = kategoriRepository.findById(kategoriid).orElse(null);

        if (kategoriBean == null) return null;
        return kategoriBean.getProsjekter();
    }

    @Override
    public void addKategori(String kategoriNavn) {
        //Todo Implement this
    }

    @Override
    public KategoriBean getKategori(int id) {
        //Todo Implement this
        return null;
    }

    @Override
    public KategoriBean getKategoriByName(String kategorinavn) {
        //Todo Implement this
        return null;
    }

    @Override
    public boolean exists(String kategorinavn) {
        //Todo Implement this
        return false;
    }

    @Override
    public boolean exists(int kategoriid) {
        //Todo Implement this
        return false;
    }

    @Override
    public void removeKategori(KategoriBean kategori) {
        //Todo Implement this
    }
}
