package kata.preproject.task3_1_3.service;

import jakarta.transaction.Transactional;
import kata.preproject.task3_1_3.dao.RoleRepository;
import kata.preproject.task3_1_3.dao.UserRepository;
import kata.preproject.task3_1_3.model.Role;
import kata.preproject.task3_1_3.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }
    @Transactional
    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(null);
    }

}
