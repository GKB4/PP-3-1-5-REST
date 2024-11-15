package kata.preproject.task3_1_3.config;


import io.micrometer.common.util.StringUtils;
import kata.preproject.task3_1_3.model.Role;
import kata.preproject.task3_1_3.service.RoleService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRoleConverter implements Converter<String, Role> {

    private final RoleService roleService;

    public StringToRoleConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return roleService.getRole(Long.parseLong(str));
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super Role, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
