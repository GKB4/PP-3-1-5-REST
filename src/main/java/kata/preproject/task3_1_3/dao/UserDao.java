package kata.preproject.task3_1_3.dao;

import kata.preproject.task3_1_3.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    void update(User user);

    List<User> findAll();

    void deleteById(long id);

    User findByUsername(String username);

    User findById(long id);
}
