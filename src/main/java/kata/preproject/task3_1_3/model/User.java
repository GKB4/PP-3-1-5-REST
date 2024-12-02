package kata.preproject.task3_1_3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 3, max = 20, message = "Size of name should be from 3 to 20 letters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Last name shouldn't be empty")
    @Size(min = 3, max = 20, message = "Size of last name should be from 3 to 20 letters")
    @Column(name = "secondName")
    private String secondName;

    @NotNull
    @Min(value = 3, message = "Age should be more then 3")
    @Column(name = "age")
    private int age;

    @NotNull(message = "Email shouldn't be empty")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password shouldn't be empty")
    @Size(min = 3, message = "Password size should be more then 3 letters")
    @Column(name = "psw")
    private String password;

    @NotEmpty(message = "Role shouldn't be empty")
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> usersRoles = new ArrayList<>();

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(List<Role> usersRoles) {
        this.usersRoles = usersRoles;
    }

    public void setRole(Role role) {
        this.usersRoles.add(role);
        role.getUsers().add(this);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUsersRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(username, user.username) && Objects.equals(secondName, user.secondName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(usersRoles, user.usersRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, secondName, age, email, password, usersRoles);
    }
}
