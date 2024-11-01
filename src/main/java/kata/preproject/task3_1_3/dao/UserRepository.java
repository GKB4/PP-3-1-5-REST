package kata.preproject.task3_1_3.dao;

import kata.preproject.task3_1_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //User findByUsername(String username);
}
