package kata.preproject.task3_1_3.service;

import kata.preproject.task3_1_3.dao.RoleRepository;
import kata.preproject.task3_1_3.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }   /////Это в Role

    @Transactional
    public List<Role> findAllRoles() {  ////Это в Role
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)   /////Это в Role
    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
