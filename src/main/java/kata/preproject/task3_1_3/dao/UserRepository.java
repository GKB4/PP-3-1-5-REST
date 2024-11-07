package kata.preproject.task3_1_3.dao;

import kata.preproject.task3_1_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u left join fetch u.usersRoles where u.username=:username")
    User findByUsername(String username);

    @Query("Select u from User u left join fetch u.usersRoles where u.id=:id")
    User findById(long id);


}
