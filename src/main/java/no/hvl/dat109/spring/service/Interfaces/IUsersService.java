package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.UsersBean;
import org.springframework.data.repository.CrudRepository;

public interface IUsersService {

    String test();

    UsersBean createNewUser(String username);

    UsersBean getUserByName(String username);

    void removeUser(UsersBean user);

    UsersBean getUserById(int id);
}
