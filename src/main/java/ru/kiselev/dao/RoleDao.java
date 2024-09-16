package ru.kiselev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kiselev.model.Role;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
}
