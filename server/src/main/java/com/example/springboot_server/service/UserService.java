package com.example.springboot_server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot_server.api.models.User;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>(Arrays.asList(
                new User(1, "Ida", 32, "ida@gmail.com"),
                new User(2, "John", 25, "john@gmail.com"),
                new User(3, "Jane", 29, "jane@yahoo.com"),
                new User(4, "Alice", 32, "alice@gmail.com"),
                new User(5, "Hans", 32, "hans@gmail.com")));
    }

    public Optional<User> findById(Integer id) {
        Optional<User> optional = Optional.empty();
        for (User user : userList)
            if (id == user.getId())
                optional = Optional.of(user);
        return optional;
    }

    public Optional<List<User>> find() {
        return Optional.of(userList);
    }
}
