package org.toepfer.foodApp.db.repository;

import org.toepfer.foodApp.db.entity.UserEntity;
import org.springframework.data.repository.Repository;

/**
 * Created by toepfer on 8/21/2017.
 */
public interface UserRepository extends Repository<UserEntity,String> {

    UserEntity findOne(String username);

}
