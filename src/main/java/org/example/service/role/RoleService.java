package org.example.service.role;

import org.example.model.Role;

public interface RoleService {
    void addRole(Role role);

    Role getRoleByName(String name);
}
