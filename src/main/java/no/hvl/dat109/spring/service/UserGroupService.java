package no.hvl.dat109.spring.service;


import no.hvl.dat109.spring.beans.UserGroupBean;
import no.hvl.dat109.spring.repository.UserGroupRepository;
import no.hvl.dat109.spring.service.Interfaces.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService implements IUserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public String test() {

        userGroupRepository.findAll().forEach(System.out::println);
        return "";
    }

    @Override
    public UserGroupBean getUsergroupById(int id) {
        return userGroupRepository.findById(id).orElse(null);
    }
}
