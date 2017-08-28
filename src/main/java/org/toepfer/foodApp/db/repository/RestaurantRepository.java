package org.toepfer.foodApp.db.repository;

import org.toepfer.foodApp.db.entity.RestaurantEntity;
import org.toepfer.foodApp.db.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by toepfer on 8/25/2017.
 */
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Integer> {

    RestaurantEntity findOneByRestaurantIdAndVotingSession(Integer restaurantId ,VotingSession votingSession);

}
