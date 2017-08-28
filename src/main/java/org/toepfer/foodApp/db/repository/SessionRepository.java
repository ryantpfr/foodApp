package org.toepfer.foodApp.db.repository;

import org.toepfer.foodApp.db.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by toepfer on 8/24/2017.
 */
public interface SessionRepository extends JpaRepository<VotingSession,Integer>{


    @Modifying
    @Query("UPDATE VotingSession SET active = false")
    void markSessionsInactive();

    VotingSession findOneByActiveTrue();

}
