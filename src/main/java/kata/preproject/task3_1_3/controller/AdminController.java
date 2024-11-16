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
    public String printUsers(ModelMap model, Principal principal) {
        model.addAttribute("usersList", userService.getUsers());
        model.addAttribute("principal", principal.getName());
        model.addAttribute("auth_roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "admin/index";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin/new";
    }

    @PostMapping(value = "/newuser")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.findAllRoles());
            return "admin/new";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update")
    public String viewUser(@RequestParam long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin/update";
    }

    @PostMapping(value = "/updateuser")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.findAllRoles());
            return "admin/update";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/userid")
    public String show(@RequestParam int id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show_adm";
    }
}
