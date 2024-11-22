package kata.preproject.task3_1_3.controller;

import jakarta.validation.Valid;
import kata.preproject.task3_1_3.model.User;
import kata.preproject.task3_1_3.service.RoleService;
import kata.preproject.task3_1_3.service.UserService;
import kata.preproject.task3_1_3.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String printUsers(@ModelAttribute("user") User user, ModelMap model, Principal principal) {
        model.addAttribute("usersList", userService.getUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("principalmail", userService.getUser(principal.getName()).getEmail());
        model.addAttribute("auth_roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("principal", userService.getUser(principal.getName()));
        return "admin/index";
    }

    @PostMapping(value = "/newuser")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "redirect:/admin";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/updateuser")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) throws Exception {
         if (result.hasErrors()) {
            return "redirect:admin/";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
