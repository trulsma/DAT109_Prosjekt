package no.hvl.dat109.spring.repository;

import no.hvl.dat109.spring.beans.StemmeMetodeBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StemmeMetodeRepository extends CrudRepository<StemmeMetodeBean, Integer> {
    //Done
}
