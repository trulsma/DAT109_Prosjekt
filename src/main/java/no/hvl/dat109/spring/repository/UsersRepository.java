package no.hvl.dat109.spring.repository;

import no.hvl.dat109.spring.beans.UsersBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UsersBean, Integer> {
}
