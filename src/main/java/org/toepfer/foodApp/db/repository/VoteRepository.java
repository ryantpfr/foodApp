package org.toepfer.foodApp.db.repository;

import org.toepfer.foodApp.db.entity.RestaurantEntity;
import org.toepfer.foodApp.db.entity.UserEntity;
import org.toepfer.foodApp.db.entity.VoteEntity;
import org.toepfer.foodApp.db.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by toepfer on 8/25/2017.
 */
public interface VoteRepository extends JpaRepository<VoteEntity,Long> {

    VoteEntity findByUserEntityAndVotingSession(UserEntity userEntity, VotingSession votingSession);

    int countByRestaurantEntity(RestaurantEntity restaurantEntity);
}
