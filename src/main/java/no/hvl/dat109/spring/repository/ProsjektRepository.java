package no.hvl.dat109.spring.repository;

import no.hvl.dat109.spring.beans.ProsjektBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProsjektRepository extends CrudRepository<ProsjektBean, Integer> {
    //DONE
}
