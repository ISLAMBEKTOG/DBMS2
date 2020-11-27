package org.example.service.user;

import org.example.dao.role.RoleDAO;
import org.example.dao.user.UserDAO;
import org.example.dao.user.UserDAOImpl;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final UserDAOImpl userDAOImpl;
    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, UserDAOImpl userDAOImpl, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.userDAOImpl = userDAOImpl;
        this.roleDAO = roleDAO;
    }

    @Override
    public void registrationUser(User user) {
        User userFromDB = userDAO.findUserByUsername(user.getUsername());

        if (userFromDB == null) {
            Role role = roleDAO.findRoleByName("USER");
            user.setRole(role);
            userDAOImpl.addUser(user);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        User userDB = userDAO.findUserByUsername(user.getUsername());

        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setUsername(user.getUsername());
        userDB.setPassword(user.getPassword());
        userDB.setAge(user.getAge());
        userDB.setEmail(user.getEmail());

        userDAOImpl.updateUser(userDB);
    }

    @Override
    public List<User> getAllUsersFromUserBucketBySpecialistId(Long id) {
        return userDAOImpl.getAllUsersFromUserBucketBySpecialistId(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDAOImpl.getUserById(id);
    }
}
