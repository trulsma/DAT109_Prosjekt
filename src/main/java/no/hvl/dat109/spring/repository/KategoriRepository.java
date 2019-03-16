package no.hvl.dat109.spring.repository;

import no.hvl.dat109.spring.beans.KategoriBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriRepository extends CrudRepository<KategoriBean, Integer> {
    //Done
}
