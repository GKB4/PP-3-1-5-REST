package kata.preproject.task3_1_3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import kata.preproject.task3_1_3.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT t FROM Role t", Role.class);
        return query.getResultList();
    }

    @Override
    public Optional<Role> findById(long id) {
        Query query = entityManager.createQuery("Select u from Role u where u.id=:id", Role.class);
        query.setParameter("id", id);
        return Optional.of((Role) query.getSingleResult());
    }

    @Override
    public Optional<Role> findByName(String roleName) {
        Query query = entityManager.createQuery("Select u from Role u where u.name=:rolename", Role.class);
        query.setParameter("rolename", roleName);
        return Optional.of((Role) query.getSingleResult());
    }
}
