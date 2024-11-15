package kata.preproject.task3_1_3.controller;

import kata.preproject.task3_1_3.service.RoleService;
import kata.preproject.task3_1_3.service.UserService;
import kata.preproject.task3_1_3.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String show(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUser(principal.getName()));
        return "user/show";
    }
}
