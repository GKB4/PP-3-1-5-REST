package kata.preproject.task3_1_3.testData;

import jakarta.transaction.Transactional;
import kata.preproject.task3_1_3.model.Role;
import kata.preproject.task3_1_3.model.User;
import kata.preproject.task3_1_3.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateTestData {
    public final UserService userService;

    public CreateTestData(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void fillDB() {
        Role role1 = new Role();
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        userService.save(role1);
        userService.save(role2);

        User user1 = new User();
        user1.setName("Jack");
        user1.setSecondName("Daniels");
        user1.setAge(4);
        user1.setPassword("123456");
        user1.setRole(role1);

        User user2 = new User();
        user2.setName("Erick");
        user2.setSecondName("Cartman");
        user2.setAge(8);
        user2.setPassword("123456");
        user2.setRole(role2);
        user2.setRole(role1);

        User user3 = new User();
        user3.setName("Admin");
        user3.setSecondName("Admin");
        user3.setAge(39);
        user3.setPassword("123456");
        user3.setRole(role2);

        User user4 = new User();
        user4.setName("User");
        user4.setSecondName("User");
        user4.setAge(39);
        user4.setPassword("123456");
        user4.setRole(role1);

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
        userService.saveUser(user4);
    }
}
