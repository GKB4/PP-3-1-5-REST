package kata.preproject.task3_1_3.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "secondName")
    private String secondName;
    @Column(name = "age")
    private int age;
    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    Set<Role> usersRoles = new HashSet<>();

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Role> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<Role> usersRoles) {
        this.usersRoles = usersRoles;
    }

    public void setRole (Role role) {
        this.usersRoles.add(role);
        role.getUsers().add(this);
    }
}
