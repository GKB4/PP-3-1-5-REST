package kata.preproject.task3_1_3.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import kata.preproject.task3_1_3.dao.RoleDAO;
import kata.preproject.task3_1_3.dao.UserDao;
import kata.preproject.task3_1_3.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDAO roleDAO) {
        this.userDao = userDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Transactional
    public void deleteUser(long id) {
        userDao.deleteById(id);
    }

    @Transactional
    public User updateUser(User user) {
        if (Objects.equals(getUserById(user.getId()).getPassword(), user.getPassword())) {
            userDao.update(user);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.update(user);
        }
        userDao.update(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found");
        }
        return user;
    }

}
