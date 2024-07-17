package com.example.springboot_server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot_server.api.models.User;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>(
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
        for (User user : users)
            if (id == user.getId())
                optional = Optional.of(user);
        return optional;
    }

    public List<User> find() {
        return users;
    }

    public List<User> deleteMany() {
        users.clear();
        return users;
    }

    public User create(User user) {
        int id = users.isEmpty() ? 1 : users.get(users.size() - 1).getId() + 1;
        User newUser = new User(id, user.getName(), user.getAge(), user.getEmail());
        users.add(newUser);
        return newUser;
    }

    public Optional<User> updateById(int id, User user) {
        Optional<User> existingUserOpt = findById(id);
        if (existingUserOpt.isEmpty())
            return Optional.empty();

        int index = 0;

        for (User user_ : users) {
            if (user_.getId() == id) {
                break;
            }
            index++;
        }

        users.set(index, user);
        return Optional.of(existingUserOpt.get());
    }

    public Optional<User> editById(int id, User user) {
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
        users.remove(existingUser);

        return Optional.of(existingUser);
    }
}
