package org.example.dao.user;

import org.example.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, String> {
    @Query("select u from User u join u.role ur where u.username = :userName")
    User findUserByUsername(@Param("userName") String s);
}
