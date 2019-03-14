package no.hvl.dat109.spring.repository;

import no.hvl.dat109.spring.beans.ArrangementBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementRepository extends CrudRepository<ArrangementBean, Integer> {
}
