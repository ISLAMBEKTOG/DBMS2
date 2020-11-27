package org.example.service.userresume;

import org.example.dao.userresume.UserResumeDAO;
import org.example.dao.userresume.UserResumeDAOImpl;
import org.example.model.UserResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserResumeServiceImpl implements UserResumeService {
    private final UserResumeDAO userResumeDAO;
    private final UserResumeDAOImpl userResumeDAOImpl;

    @Autowired
    public UserResumeServiceImpl(UserResumeDAO userResumeDAO, UserResumeDAOImpl userResumeDAOImpl) {
        this.userResumeDAO = userResumeDAO;
        this.userResumeDAOImpl = userResumeDAOImpl;
    }

    @Override
    public void addUserResume(UserResume userResume) {
        userResumeDAOImpl.addUserResume(userResume);
    }

    @Override
    public void deleteResume(UserResume userResume) {
        userResumeDAOImpl.deleteUserResume(userResume.getId());
    }

    @Override
    public UserResume getResume(Long userId, Long specId) {
        return userResumeDAOImpl.getUserResumesByUserIdAndSpecialistId(userId, specId);
    }
}
