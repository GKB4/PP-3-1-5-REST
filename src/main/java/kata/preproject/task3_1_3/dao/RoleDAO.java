package kata.preproject.task3_1_3.dao;

import kata.preproject.task3_1_3.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleDAO {
    void save(Role role);

    List<Role> findAll();

    Optional<Role> findById(long id);

    Optional<Role> findByName(String roleName);

}
