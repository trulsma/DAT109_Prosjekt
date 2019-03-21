package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.StudieBean;

import java.util.Iterator;

public interface IStudieService {

    String test();

    StudieBean getStudieById(int id);

    Iterable<StudieBean> getAllStudier();



}
