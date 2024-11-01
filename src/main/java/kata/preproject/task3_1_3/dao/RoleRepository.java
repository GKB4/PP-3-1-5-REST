package kata.preproject.task3_1_3.dao;

import kata.preproject.task3_1_3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
