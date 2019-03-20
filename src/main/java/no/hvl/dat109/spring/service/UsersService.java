package no.hvl.dat109.spring.service;

import no.hvl.dat109.spring.repository.UsersRepository;
import no.hvl.dat109.spring.service.Interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String test() {
        usersRepository.findAll().forEach(System.out::println);
        return "";
    }
}
