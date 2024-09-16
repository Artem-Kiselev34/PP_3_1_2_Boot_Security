package ru.kiselev.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kiselev.dao.UserDao;
import ru.kiselev.model.User;


@Component
public class DetailServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public DetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return user;
    }
}
