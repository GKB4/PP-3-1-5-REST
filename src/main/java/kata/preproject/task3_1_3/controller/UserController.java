package kata.preproject.task3_1_3.controller;

import jakarta.validation.Valid;
import kata.preproject.task3_1_3.model.Role;
import kata.preproject.task3_1_3.model.User;
import kata.preproject.task3_1_3.service.RoleService;
import kata.preproject.task3_1_3.service.UserService;
import kata.preproject.task3_1_3.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/error")
    public String hello() {
        return "error";
    }

    @GetMapping(value = "/admin")
    public String printUsers(ModelMap model, Principal principal) {
        model.addAttribute("usersList", userService.getUsers());
        model.addAttribute("principal", principal.getName());
        model.addAttribute("auth_roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "admin";
    }

    @GetMapping(value = "/user")
    public String show(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUser(principal.getName()));
        return "show";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(@ModelAttribute("user") User user, ModelMap model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        return "/new";
    }

    @PostMapping(value = "admin/newuser")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("roles", roles);
            return "/new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/update")
    public String viewUser(@RequestParam long id, ModelMap model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", userService.getUserById(id));
        return "/update";
    }

    @PostMapping(value = "/admin/updateuser")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("roles", roles);
            return "/update";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/userid")
    public String show(@RequestParam int id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/show_adm";
    }

}
