package org.example.dao.userresume;

import org.example.model.UserResume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResumeDAO extends CrudRepository<UserResume, String> {
}
