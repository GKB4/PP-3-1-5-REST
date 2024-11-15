package kata.preproject.task3_1_3.service;

import kata.preproject.task3_1_3.model.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);

    List<Role> findAllRoles();

    Role getRole(long id);
}
