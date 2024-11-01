package kata.preproject.task3_1_3;

import kata.preproject.task3_1_3.service.UserService;
import kata.preproject.task3_1_3.testData.CreateTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Task313Application {


	public static void main(String[] args) {
		SpringApplication.run(Task313Application.class, args);
	}

	@Bean
	CommandLineRunner init(CreateTestData createTestData) {
		return args -> {createTestData.fillDB();};
	}
}
