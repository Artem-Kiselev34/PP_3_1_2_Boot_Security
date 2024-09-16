package ru.kiselev.Initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kiselev.dao.RoleDao;
import ru.kiselev.model.Role;
import ru.kiselev.model.User;
import ru.kiselev.service.UserService;


import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UserAndRoleSeeder {


    private final RoleDao roleDao;
    private final UserService userService;


    @Autowired
    public UserAndRoleSeeder(RoleDao roleDao, UserService userService) {
        this.roleDao = roleDao;
        this.userService = userService;
    }


    @PostConstruct
    private void init() {
        roleDao.save(new Role(1L, "ROLE_ADMIN"));
        roleDao.save(new Role(2L, "ROLE_USER"));
        List<Role> adminRole = roleDao.findById(1L).stream().toList();
        List<Role> userRole = roleDao.findById(2L).stream().toList();
        userService.createUser(new User("admin", "admin", 33, "admin@mail.ru", "admin", adminRole));
        userService.createUser(new User("user", "user", 44, "user@mail.ru", "user", userRole));
    }
}
