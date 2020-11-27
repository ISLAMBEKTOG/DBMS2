package org.example.dao.userbucket;

import org.example.model.UserBucket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBucketDAO extends CrudRepository<UserBucket, String> {
}
