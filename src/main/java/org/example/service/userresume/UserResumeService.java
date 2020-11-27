package org.example.service.userresume;

import org.example.model.UserResume;

public interface UserResumeService {
    void addUserResume(UserResume userResume);

    void deleteResume(UserResume userResume);

    UserResume getResume(Long userId, Long specId);
}
