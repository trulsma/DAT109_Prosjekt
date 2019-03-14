package no.hvl.dat109.spring.repository;

import no.hvl.dat109.spring.beans.ArrangementBean;
import no.hvl.dat109.spring.beans.ArrangementdeltagelseBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementdeltagelseRepository extends CrudRepository<ArrangementdeltagelseBean, Integer> {
}
