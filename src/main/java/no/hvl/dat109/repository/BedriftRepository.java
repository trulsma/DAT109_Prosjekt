package no.hvl.dat109.repository;


import no.hvl.dat109.bean.Bedrift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedriftRepository extends CrudRepository<Bedrift, Integer> {

}
