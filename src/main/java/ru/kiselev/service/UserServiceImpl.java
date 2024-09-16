package ru.kiselev.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.kiselev.dao.UserDao;
import ru.kiselev.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userDao.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(user.getRoles());
        userDao.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User updatedUser) {
        Optional<User> userFromDb = userDao.findById(id);
        if (userFromDb.isPresent()) {
            User existingUser = userFromDb.get();

            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAge(updatedUser.getAge());

            if (!updatedUser.getPassword().equals(existingUser.getPassword())) {
                existingUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
            }

            existingUser.setRoles(updatedUser.getRoles());

            userDao.save(existingUser);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if (userDao.findById(userId).isPresent()) {
            userDao.deleteById(userId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}