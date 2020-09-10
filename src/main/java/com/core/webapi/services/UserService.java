package com.core.webapi.services;

import com.core.webapi.model.User;
import com.core.webapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getUsers(String username) {
        List<User> users;
        if (!StringUtils.isEmpty(username))
            users = userRepository.getByUsernameLike(username + "%");
        else
            users = userRepository.findAll();

        // manipulate results...

        return CollectionUtils.isEmpty(users) ? Collections.emptyList() : users;
    }
    public User getById(Long id) {
        User user = userRepository.getById(id);

        // manipulate results...

        return user == null ? new User() : user;
    }

    public User getByUsername(String username) {
        User user = userRepository.getByUsername(username);

        // manipulate results...

        return user == null ? new User() : user;
    }
}
