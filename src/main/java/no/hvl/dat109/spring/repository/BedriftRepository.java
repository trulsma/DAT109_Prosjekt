package no.hvl.dat109.spring.repository;


import no.hvl.dat109.spring.beans.BedriftBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedriftRepository extends CrudRepository<BedriftBean, Integer> {
    //DONE
}
