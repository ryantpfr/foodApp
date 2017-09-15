package org.toepfer.foodApp.db.entity;

import javax.persistence.*;

/**
 * Created by toepfer on 8/24/2017.
 */
@Entity
@Table(name = "VOTE_T")
public class VoteEntity {

    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "USER")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private RestaurantEntity restaurantEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }
}
