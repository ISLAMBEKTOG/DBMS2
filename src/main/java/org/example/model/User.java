package org.example.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllUsers",
                query = "{? = call USER_PAC.FINDALL()}",
                resultClass = User.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "getAllUsersFromUserBucketBySpecialistId",
                query = "{? = call USER_PAC.GETALLUSERSFROMUSERBUCKETBYSPECIALISTID(:specId)}",
                resultClass = User.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "getUserById",
                query = "{? = call USER_PAC.GETUSERBYID(:userId)}",
                resultClass = User.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")})
})

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "addUser",
                procedureName = "USER_PAC.ADDUSER",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_age"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_first_name"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_last_name"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_username"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_roleId")
                }
        ),
        @NamedStoredProcedureQuery(
                name = "updateUser",
                procedureName = "USER_PAC.UPDATEUSER",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_age"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_first_name"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_last_name"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_username"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_roleId")
                }
        )
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName, Integer age, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public User(String username, String password, String firstName, String lastName, Integer age, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public User(String username, String password, String firstName, String lastName, Integer age, String email, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(getRole());
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
