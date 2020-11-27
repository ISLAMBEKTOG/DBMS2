package org.example.dao.role;

import org.example.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends CrudRepository<Role, String> {
    Role findRoleByName(String s);
}
