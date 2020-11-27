package org.example.service.userbucket;

import org.example.dao.category.CategoryDAOImpl;
import org.example.dao.specialist.SpecialistDAOImpl;
import org.example.dao.specialist.SpecialistsDAO;
import org.example.dao.userbucket.UserBucketDAO;
import org.example.dao.userbucket.UserBucketDAOImpl;
import org.example.model.Category;
import org.example.model.Specialists;
import org.example.model.UserBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBucketServiceImpl implements UserBucketService {
    private final UserBucketDAO userBucketDAO;
    private final UserBucketDAOImpl userBucketDAOImpl;
    private final SpecialistsDAO specialistsDAO;
    private final SpecialistDAOImpl specialistDAO;
    private final CategoryDAOImpl categoryDAO;

    @Autowired
    public UserBucketServiceImpl(UserBucketDAO userBucketDAO, UserBucketDAOImpl userBucketDAOImpl, SpecialistsDAO specialistsDAO, SpecialistDAOImpl specialistDAO, CategoryDAOImpl categoryDAO) {
        this.userBucketDAO = userBucketDAO;
        this.userBucketDAOImpl = userBucketDAOImpl;
        this.specialistsDAO = specialistsDAO;
        this.specialistDAO = specialistDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Specialists> getSpecialistsByUserId(Long id) {
        return specialistDAO.findSpecialistsByUserId(id);
    }

    @Override
    public void addSpecialistToUserBucket(UserBucket userBucket) {
        userBucketDAOImpl.addUserBucket(userBucket);
    }

    @Override
    public List<Category> getAllCategoriesByUserId(Long id) {
        return categoryDAO.getAllCategoriesByUserId(id);
    }

    @Override
    public void deleteSpecialistAndUser(UserBucket userBucket) {
        userBucketDAOImpl.deleteUserBucket(userBucket.getId());
    }

    @Override
    public UserBucket getUserBucketByUserIdAndSpecId(Long userId, Long specId) {
        return userBucketDAOImpl.getUserBucketByUserIdAndSpecialistId(userId, specId);
    }

    @Override
    public List<UserBucket> getAllUserBucketsBySpecialistId(Long id) {
        return userBucketDAOImpl.getAllUserBucketsBySpecialistId(id);
    }
}
