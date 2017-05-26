package cz.tul.service;

import com.google.common.collect.Lists;
import cz.tul.data.User;
import cz.tul.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Martin on 30.04.2017.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(User user) {userRepository.save(user);}

    public User getUser(int id) {
        return userRepository.findOne(id);
    }

    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public void deleteUsers() {userRepository.deleteAll();}
}
