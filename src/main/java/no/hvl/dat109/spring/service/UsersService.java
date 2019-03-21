package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.beans.UsersBean;
import no.hvl.dat109.spring.repository.UsersRepository;
import no.hvl.dat109.spring.service.Interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserGroupService userGroupService;

    @Override
    public String test() {
        usersRepository.findAll().forEach(System.out::println);
        return "";
    }

    @Override
    public UsersBean createNewUser(String username) {
        UsersBean user = new UsersBean(username, userGroupService.getUsergroupById(2));
        usersRepository.save(user);
        return getUserByName(username);
    }

    @Override
    public UsersBean getUserByName(String username) {
        Iterator<UsersBean> users = usersRepository.findAll().iterator();

        UsersBean user;
        while (users.hasNext()) {
            user = users.next();
            if (user.getUsername().equals(username)) return user;
        }

        return null;
    }

    @Override
    public void removeUser(UsersBean user) {
        usersRepository.delete(user);
    }
}
