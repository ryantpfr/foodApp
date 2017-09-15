package org.toepfer.foodApp.db.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by toepfer on 8/24/2017.
 */
@Entity
@Table(name = "SESSION_T")
public class VotingSession {

    @Id
    @GeneratedValue
    @Column(name = "SESSION_ID")
    private Integer sessionId;

    @Column(name = "ACTIVE")
    private Boolean active;

    @OneToMany(mappedBy = "votingSession", cascade = CascadeType.ALL)
    private List<RestaurantEntity> restaurantEntities;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<RestaurantEntity> getRestaurantEntities() {
        return restaurantEntities;
    }

    public void setRestaurantEntities(List<RestaurantEntity> restaurantEntities) {
        this.restaurantEntities = restaurantEntities;
    }
}
