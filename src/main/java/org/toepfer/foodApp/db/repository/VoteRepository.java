package org.toepfer.foodApp.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.toepfer.foodApp.db.entity.RestaurantEntity;
import org.toepfer.foodApp.db.entity.UserEntity;
import org.toepfer.foodApp.db.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.toepfer.foodApp.db.entity.VotingSession;

/**
 * Created by toepfer on 8/25/2017.
 */
public interface VoteRepository extends JpaRepository<VoteEntity,Long> {

    //VoteEntity findByUserEntityAndVotingSession(UserEntity userEntity, VotingSession votingSession);

    @Query("SELECT ve " +
            "FROM VotingSession vs " +
            "JOIN vs.restaurantEntities re " +
            "JOIN re.voteEntities ve " +
            "WHERE vs = ?2 " +
            "AND ve.userEntity = ?1 ")
    VoteEntity findBySessionAndUser(UserEntity username, VotingSession votingSession);

    int countByRestaurantEntity(RestaurantEntity restaurantEntity);
}
