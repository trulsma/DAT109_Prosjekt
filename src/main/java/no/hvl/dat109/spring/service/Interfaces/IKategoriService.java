package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.KategoriBean;
import no.hvl.dat109.spring.beans.ProsjektBean;

import java.util.List;

public interface IKategoriService {

    //Todo create service methods for categories

    String getAllKategorierAsString();

    Iterable<KategoriBean> getAllKategorier();

    List<ProsjektBean> getAllProsjektWithKategori(int kategoriid);

    void addKategori(String kategoriNavn);

    KategoriBean getKategori(int id);

    KategoriBean getKategoriByName(String kategorinavn);

    boolean exists(String kategorinavn);

    boolean exists(int kategoriid);

    void removeKategori(KategoriBean kategori);
}
