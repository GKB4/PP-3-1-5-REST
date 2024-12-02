package kata.preproject.task3_1_3.config;

import kata.preproject.task3_1_3.model.Role;
import kata.preproject.task3_1_3.service.RoleService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final RoleService roleService;

    public MvcConfig(RoleService roleService) {
        this.roleService = roleService;
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin/index.html");
        registry.addViewController("/user").setViewName("user/show.html");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter((Converter<String, Role>) new StringToRoleConverter(roleService));
    }
}
