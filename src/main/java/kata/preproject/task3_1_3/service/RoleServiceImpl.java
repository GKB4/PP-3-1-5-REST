package kata.preproject.task3_1_3.service;

import kata.preproject.task3_1_3.dao.RoleDAO;
import kata.preproject.task3_1_3.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Transactional
    public List<Role> findAllRoles() {
        return roleDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Role getRole(long id) {
        return roleDAO.findById(id).orElse(null);
    }
}
