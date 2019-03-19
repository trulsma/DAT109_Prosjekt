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
        return kategoriRepository.findAll();
    }

    @Override
    public List<ProsjektBean> getAllProsjektWithKategori(int kategoriid) {
        KategoriBean kategoriBean = kategoriRepository.findById(kategoriid).orElse(null);

        if (kategoriBean == null) return null;
        return kategoriBean.getProsjekter();
    }

    @Override
    public void addKategori(String kategoriNavn) {
       KategoriBean nyBean = new KategoriBean(kategoriNavn);
       kategoriRepository.save(nyBean);
    }

    @Override
    public KategoriBean getKategori(int id) {
        return kategoriRepository.findById(id).orElse(null);
    }

    @Override
    public KategoriBean getKategoriByName(String kategorinavn) {
        for(KategoriBean k : kategoriRepository.findAll()){
            if(k.getKategorinavn().equals(kategorinavn)) return k;
            }
                return null;
            }




    @Override
    public boolean exists(String kategorinavn) {
        for(KategoriBean k : kategoriRepository.findAll()){
            if(k.getKategorinavn().equals(kategorinavn)) return true;
        }
        return false;
    }

    @Override
    public boolean exists(int kategoriid) {
       KategoriBean k = kategoriRepository.findById(kategoriid).orElse(null);
        return k != null;
    }

    @Override
    public void removeKategori(KategoriBean kategori) {
       kategoriRepository.delete(kategori);
    }
}
