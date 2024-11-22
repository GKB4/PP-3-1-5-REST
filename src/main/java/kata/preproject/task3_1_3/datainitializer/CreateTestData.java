package kata.preproject.task3_1_3.datainitializer;

import jakarta.transaction.Transactional;
import kata.preproject.task3_1_3.model.Role;
import kata.preproject.task3_1_3.model.User;
import kata.preproject.task3_1_3.service.RoleService;
import kata.preproject.task3_1_3.service.UserService;
import kata.preproject.task3_1_3.service.UserServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateTestData {
    public final UserService userService;
    public final RoleService roleService;

    public CreateTestData(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    public void fillDB() {
        Role role1 = new Role();
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        roleService.save(role1);
        roleService.save(role2);

        User user1 = new User();
        user1.setName("Admin");
        user1.setSecondName("Admin");
        user1.setAge(39);
        user1.setEmail("admin@mail.ru");
        user1.setPassword("123456");
        user1.setRole(role2);
        user1.setRole(role1);

        User user2 = new User();
        user2.setName("User");
        user2.setSecondName("User");
        user2.setAge(39);
        user2.setEmail("user@mail.ru");
        user2.setPassword("123456");
        user2.setRole(role1);

        userService.save(user1);
        userService.save(user2);
    }
}
