package kata.preproject.task3_1_3.service;

import kata.preproject.task3_1_3.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(User user);
    boolean saveUser(User user);
    User getUser(String username);
    User getUserById(long id);
    List<User> getUsers();
    void deleteUser(long id);
    void updateUser(User user);
}
