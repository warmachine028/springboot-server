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
        userList = new ArrayList<>(
                Arrays.asList(
                        new User(1, "Ida", 32, "ida@gmail.com"),
                        new User(2, "John", 25, "john@gmail.com"),
                        new User(3, "Jane", 29, "jane@yahoo.com"),
                        new User(4, "Alice", 32, "alice@gmail.com"),
                        new User(5, "Hans", 32, "hans@gmail.com") //
                ) //
        );
    }

    public Optional<User> findById(int id) {
        Optional<User> optional = Optional.empty();
        for (User user : userList)
            if (id == user.getId())
                optional = Optional.of(user);
        return optional;
    }

    public Optional<List<User>> find() {
        return Optional.of(userList);
    }

    public User create(User user) {
        int id = userList.isEmpty() ? 1 : userList.get(userList.size() - 1).getId() + 1;
        User newUser = new User(id, user.getName(), user.getAge(), user.getEmail());
        userList.add(newUser);
        return newUser;
    }

    public Optional<User> update(int id, User user) {
        Optional<User> existingUserOpt = findById(id);
        if (existingUserOpt.isEmpty())
            return Optional.empty();

        User existingUser = existingUserOpt.get();
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getAge() != 0) {
            existingUser.setAge(user.getAge());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        return Optional.of(existingUser);
    }

    public Optional<User> deleteById(int id) {
        Optional<User> existingUserOpt = findById(id);

        if (existingUserOpt.isEmpty())
            return Optional.empty();

        User existingUser = existingUserOpt.get();
        userList.remove(existingUser);

        return Optional.of(existingUser);
    }
}
