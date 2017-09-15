package org.toepfer.foodApp.db.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by toepfer on 8/24/2017.
 */
@Entity
@Table(name = "restaurant_t")
public class RestaurantEntity {

    @Id
    @GeneratedValue
    @Column(name = "RESTAURANT_ID")
    private int restaurantId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "SESSION_ID")
    private VotingSession votingSession;

    @OneToMany(mappedBy = "restaurantEntity")
    private Set<VoteEntity> voteEntities;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VotingSession getVotingSession() {
        return votingSession;
    }

    public void setVotingSession(VotingSession votingSession) {
        this.votingSession = votingSession;
    }

    public Set<VoteEntity> getVoteEntities() {
        return voteEntities;
    }

    public void setVoteEntities(Set<VoteEntity> voteEntities) {
        this.voteEntities = voteEntities;
    }
}
