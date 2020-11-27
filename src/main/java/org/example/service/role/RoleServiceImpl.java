package org.example.service.role;

import org.example.dao.role.RoleDAO;
import org.example.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public void addRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.findRoleByName(name);
    }
}
