package kata.preproject.task3_1_3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import kata.preproject.task3_1_3.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final RoleDaoImpl roleDao;

    public UserDaoImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT t FROM User t left join fetch t.usersRoles", User.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery("Select u from User u left join fetch u.usersRoles where u.username=:username", User.class);
        query.setParameter("username", username);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (User) query.getSingleResult();
    }

    @Override
    public User findById(long id) {
        Query query = entityManager.createQuery("Select u from User u left join fetch u.usersRoles where u.id=:id", User.class);
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (User) query.getSingleResult();
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = entityManager.createQuery("Select u from User u left join fetch u.usersRoles where u.email=:email", User.class);
        query.setParameter("email", email);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (User) query.getSingleResult();
    }
}
