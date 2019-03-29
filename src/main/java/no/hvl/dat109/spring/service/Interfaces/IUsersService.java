package no.hvl.dat109.spring.service.Interfaces;

import no.hvl.dat109.spring.beans.UsersBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsersService {

    String test();

    UsersBean createNewUser(String username, String password);

    UsersBean getUserByName(String username);

    UsersBean validUser(String username, String password);

    List<UsersBean> getUsersWithName(String username);

    void createVoterUser(String username, String ipadress);

    void removeUser(UsersBean user);

    UsersBean getUserById(int id);

    UsersBean getVoterUserByName(String epost);
}
