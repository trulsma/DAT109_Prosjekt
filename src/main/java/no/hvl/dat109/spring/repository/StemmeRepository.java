package no.hvl.dat109.spring.repository;

import no.hvl.dat109.spring.beans.StemmeBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StemmeRepository extends CrudRepository<StemmeBean, Integer> {
    //DONE
}
