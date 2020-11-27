package org.example.service.userbucket;

import org.example.model.Category;
import org.example.model.Specialists;
import org.example.model.UserBucket;

import java.util.List;

public interface UserBucketService {
    List<Specialists> getSpecialistsByUserId(Long id);

    void addSpecialistToUserBucket(UserBucket userBucket);

    List<Category> getAllCategoriesByUserId(Long id);

    void deleteSpecialistAndUser(UserBucket userBucket);

    UserBucket getUserBucketByUserIdAndSpecId(Long userId, Long specId);

    List<UserBucket> getAllUserBucketsBySpecialistId(Long id);
}
