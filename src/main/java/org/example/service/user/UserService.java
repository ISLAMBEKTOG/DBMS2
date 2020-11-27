package org.example.service.user;


import org.example.model.User;

import java.util.List;

public interface UserService {
    void registrationUser(User user);

    User getUserByUsername(String username);

    void updateUser(User user);

    List<User> getAllUsersFromUserBucketBySpecialistId(Long id);

    User getUserById(Long id);
}
