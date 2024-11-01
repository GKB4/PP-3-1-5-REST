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

        User user1 = new User();
        user1.setName("Jack");
        user1.setSecondName("Daniels");
        user1.setAge(4);

        User user2 = new User();
        user2.setName("Erick");
        user2.setSecondName("Cartman");
        user2.setAge(8);

        userService.save(role1);
        userService.save(role2);
        userService.save(user1);
        userService.save(user2);

        user1.setRole(role1);
        user2.setRole(role2);

    }
}
