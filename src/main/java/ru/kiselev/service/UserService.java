package ru.kiselev.service;

import ru.kiselev.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();

    public User findUserById(Long userId);

    public void createUser(User user);

    public void updateUser(Long id, User user);

    public void deleteUser(Long userId);

    User findByEmail(String email);

}
